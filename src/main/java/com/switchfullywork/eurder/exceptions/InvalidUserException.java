package com.switchfullywork.eurder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "This is not a valid user.";

    public InvalidUserException() {
        super(DEFAULT_MESSAGE);
    }
}
