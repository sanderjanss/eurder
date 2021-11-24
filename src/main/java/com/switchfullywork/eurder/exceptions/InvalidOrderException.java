package com.switchfullywork.eurder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOrderException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "This is not a valid order.";

    public InvalidOrderException() {
        super(DEFAULT_MESSAGE);
    }
}
