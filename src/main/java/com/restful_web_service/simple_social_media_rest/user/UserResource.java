package com.restful_web_service.simple_social_media_rest.user;

import com.restful_web_service.simple_social_media_rest.exception.UserNotFoundException;
import com.restful_web_service.simple_social_media_rest.user.dao.UserDaoService;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class UserResource {
    private final UserDaoService userDaoService;
    private final MessageSource messageSource;
    public UserResource(UserDaoService userDaoService, MessageSource messageSource) {
        this.userDaoService = userDaoService;
        this.messageSource = messageSource;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        User user = userDaoService.findOne(id);
        if (user == null) throw new UserNotFoundException("id:" + id);
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid  @RequestBody User user) {
        User savedUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("users/{id}")
    public void deleteUserById(@PathVariable int id) {
        userDaoService.deleteOne(id);
    }

    @GetMapping("users/greeting-internationalization")
    public String greeting() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default message", locale);
    }
}
