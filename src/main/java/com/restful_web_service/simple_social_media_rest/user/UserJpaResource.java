package com.restful_web_service.simple_social_media_rest.user;

import com.restful_web_service.simple_social_media_rest.exception.PostNotFoundException;
import com.restful_web_service.simple_social_media_rest.exception.UserNotFoundException;
import com.restful_web_service.simple_social_media_rest.jpa.PostJpaRepository;
import com.restful_web_service.simple_social_media_rest.jpa.UserJpaRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {
    private final UserJpaRepository userJpaRepository;
    private final PostJpaRepository postJpaRepository;
    public UserJpaResource(UserJpaRepository userJpaRepository, PostJpaRepository postJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.postJpaRepository = postJpaRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userJpaRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> getUserById(@PathVariable int id) {
        Optional<User> user = userJpaRepository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("id:" + id);
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid  @RequestBody User user) {
        User savedUser = userJpaRepository.save(user);

        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserById(@PathVariable int id) {
        userJpaRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        Optional<User> user = userJpaRepository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("id:" + id);
        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostsForUser(@PathVariable int id, @Valid  @RequestBody Post post) {
        Optional<User> user = userJpaRepository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("id:" + id);
        post.setUser(user.get());
        Post savedPost = postJpaRepository.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/users/{userId}/posts/{postId}")
    public Post getPostById(@PathVariable int userId, @PathVariable int postId) {
        Optional<User> user = userJpaRepository.findById(userId);
        if (user.isEmpty()) throw new UserNotFoundException("id:" + userId);
        Post postFound = user.get().getPosts().stream().filter(post -> post.getId() == postId).findFirst().orElse(null);
        if (postFound == null) throw new PostNotFoundException("id:" + postId);
        return postFound;
    }
}
