package com.venku.spring.model;

import com.venku.spring.exceptions.UnprocessableException;

/**
 * User class
 * Created by venkatesha.chandru on 5/23/2016.
 */
public class User {

    private String username;

    private String userId;

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
}
