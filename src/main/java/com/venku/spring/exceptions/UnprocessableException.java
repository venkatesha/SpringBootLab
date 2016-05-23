package com.venku.spring.exceptions;

/**
 * Custom exception.
 * <p>
 * Created by venkatesha.chandru on 5/23/2016.
 */
public class UnprocessableException extends RuntimeException {

    protected String exceptionMessage;

    public UnprocessableException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
