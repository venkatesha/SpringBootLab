package com.venku.spring.model;

import com.venku.spring.exceptions.UnprocessableException;

import java.util.Set;

/**
 * User class
 * Created by venkatesha.chandru on 5/23/2016.
 */
public class User {

    public static final String SOME_ROLE = "";

    private String username;

    private String userId;

    private Set<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        throw new UnprocessableException("userId should not be part of request body[JSON]");
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public boolean isEqual(User user) {
        return this.equals(user);
    }

    public static boolean hasSomeRole(User user) {
        return true;
    }
}
