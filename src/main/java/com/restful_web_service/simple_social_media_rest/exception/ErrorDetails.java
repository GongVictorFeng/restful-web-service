package com.restful_web_service.simple_social_media_rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorDetails  {
    private LocalDateTime timestamp;
    private String message;
    private String details;
}
