package com.venku.spring.controller;

import static java.util.function.Predicate.isEqual;
import static javaslang.API.*;
import static javaslang.Patterns.Some;
import static javax.ws.rs.core.Response.Status.*;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import javaslang.control.Option;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.venku.spring.model.User;
import com.venku.spring.service.UserService;

/**
 * User controller
 * <p>
 * Created by venkatesha.chandru on 5/23/2016.
 */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("Init Web Data Binder");
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User submit(@RequestBody User user) {

        return user;
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public User get(@PathVariable("userId") String userId) {

        return new User();
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {

        return ResponseEntity.ok("Deleted user " + userId);

    }

    public Response doStuff(String userName) {

        Optional<User> optionalUser = userService.findUserByUsername(userName);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getRoles().contains(User.SOME_ROLE)) {
                if (userService.performSomeOperationOn(user)) {
                    return Response.status(OK).build();
                }
                return Response.status(NOT_FOUND).build();
            } else {
                User alternativeUser = userService.findUserSomeAlternativeWay();
                if (alternativeUser.equals(user)) {
                    if (userService.performSomeOperationOn(user)) {
                        return Response.status(OK).build();
                    }
                    return Response.status(NOT_FOUND).build();
                }
            }
        }
        return Response.status(FORBIDDEN).build();
    }

    public Response doStuffFunction(String userName) {

        Function<User, Response> doOperation = (user) -> {
            if (userService.performSomeOperationOn(user)) {
                return Response.status(OK).build();
            }
            return Response.status(NOT_FOUND).build();
        };

        BiFunction<User, String, Response> doOperationAsAlternativeUser = (user, secretKey) ->
                userService.findUserSomeAlternativeWay(secretKey)
                        .filter(alternativeUser -> alternativeUser.equals(user))
                        .map(alternativeUser -> doOperation.apply(user))
                        .getOrElse(Response.status(FORBIDDEN).build());

        return userService.findUserByUsernameStream(userName)
                .map(user -> {
                    if (user.getRoles().contains(User.SOME_ROLE)) {
                        return doOperation.apply(user);
                    }
                    return doOperationAsAlternativeUser.apply(user, "secretKey");
                }).getOrElse(Response.status(FORBIDDEN).build());
    }

    public Response doStuffSlang(String userName) {

        Function<User, Response> doOperation = (user) ->
                userService.performSomeOperationOn(user) ?
                        Response.status(OK).build() :
                        Response.status(NOT_FOUND).build();

        BiFunction<User, String, Response> doOperationAsAlternativeUser = (user, secretKey) -> {
            Option<User> alternativeUser = userService.findUserSomeAlternativeWayOption(secretKey);
            return javaslang.API.Match(alternativeUser).of(
                    Case(Some($(isEqual(user))), doOperation.apply(user)),
                    Case($(), Response.status(FORBIDDEN).build())
            );
        };

        Option<User> user = userService.findUserByUsernameOption(userName);
        return Match(user).of(
                Case(Some($(User::hasSomeRole)), u -> doOperation.apply(u)),
                Case(Some($()), u -> doOperationAsAlternativeUser.apply(u, "secretKey")),
                Case($(), Response.status(FORBIDDEN).build())
        );
    }

    public Response doStuffJava8(String userName) {
        Response.Status status =
                userService
                        .findUserByUsername(userName)
                        .filter(this::tryFoundAppropriateUser)
                        .map(this::perform)
                        .orElse(FORBIDDEN);

        return Response.status(status).build();
    }

    private boolean tryFoundAppropriateUser(User user) {

        return user.getRoles().contains(User.SOME_ROLE)
                || userService.findUserSomeAlternativeWay().equals(user);
    }

    private Response.Status perform(User user) {

        return userService.performSomeOperationOn(user)
                ? OK
                : NOT_FOUND;
    }
}
