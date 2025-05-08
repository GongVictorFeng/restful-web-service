package com.restful_web_service.simple_social_media_rest.user.dao;

import com.restful_web_service.simple_social_media_rest.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users= new ArrayList<>();
    private static int count = 0;

    static {
        users.add(new User(++count, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++count, "Eve", LocalDate.now().minusYears(25)));
        users.add(new User(++count, "Jim", LocalDate.now().minusYears(20)));
    }
    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        user.setId(++count);
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public void deleteOne(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}
