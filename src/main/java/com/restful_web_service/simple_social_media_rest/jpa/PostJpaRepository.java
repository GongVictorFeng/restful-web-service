package com.restful_web_service.simple_social_media_rest.jpa;

import com.restful_web_service.simple_social_media_rest.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Integer> {
}
