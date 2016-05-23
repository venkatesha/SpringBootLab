package com.venku.spring.controller;

import com.venku.spring.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller
 *
 * Created by venkatesha.chandru on 5/23/2016.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {


//    public void initBinder(WebDataBinder binder) {
//        binder.setDisallowedFields("userId");
//    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public User submit(@RequestBody User user) {

        return user;
    }


}
