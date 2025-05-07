package com.restful_web_service.simple_social_media_rest.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {
    private Integer id;
    private String name;
    private LocalDate dateOfBirth;
}
