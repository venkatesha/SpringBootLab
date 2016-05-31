package com.venku.spring.exceptions.handlers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.venku.spring.exceptions.UnprocessableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handler for exception
 * <p>
 * Created by venkatesha.chandru on 5/23/2016.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleUnprocessableException(HttpServletRequest req, HttpServletResponse res, HttpMessageNotReadableException e) {
        if (e.getCause() instanceof JsonMappingException) {
            JsonMappingException jme = (JsonMappingException) e.getCause();
            if (jme.getCause() instanceof UnprocessableException) {
                UnprocessableException ue = (UnprocessableException) jme.getCause();
                return ResponseEntity.unprocessableEntity().body(ue.getExceptionMessage());
            }

        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the request");
    }
}
