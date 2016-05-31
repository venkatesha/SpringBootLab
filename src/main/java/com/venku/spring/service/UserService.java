package com.venku.spring.service;

import com.venku.spring.model.User;
import javaslang.collection.Stream;
import javaslang.control.Option;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * User Service
 * <p>
 * Created by venkatesha.chandru on 5/25/2016.
 */
@Service
public class UserService {


    public Optional<User> findUserByUsername(String userName) {
        return Optional.of(new User());
    }

    public User findUserSomeAlternativeWay() {
        return new User();
    }

    public boolean performSomeOperationOn(User user) {
        return true;
    }

    public Stream<User> findUserSomeAlternativeWay(String secretKey) {
        return Stream.of(new User());
    }

    public Stream<User> findUserByUsernameStream(String userName) {
        return Stream.of(new User());
    }

    public Option<User> findUserSomeAlternativeWayOption(String secretKey) {
        return Option.of(new User());
    }

    public Option<User> findUserByUsernameOption(String userName) {
        return Option.of(new User());
    }
}
