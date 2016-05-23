package com.venku.spring.exceptions.handlers;

import com.venku.spring.exceptions.UnprocessableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handler for exception
 *
 * Created by venkatesha.chandru on 5/23/2016.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason="userId should not be part of request body[JSON]")
    public ResponseEntity<Object> handleUnprocessableException(HttpServletRequest req, HttpServletResponse res, Exception e) {
        return ResponseEntity.unprocessableEntity().body("");
    }
}
