package com.restful_web_service.simple_social_media_rest.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="user_details")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2)
    private String name;
    @Past
    private LocalDate dateOfBirth;
}
