package com.restful_web_service.simple_social_media_rest.user;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
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
    @Size(min = 2)
    private String name;
    @Past
    private LocalDate dateOfBirth;
}
