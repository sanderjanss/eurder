package com.switchfullywork.eurder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidItemException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "This is not a valid item.";

    public InvalidItemException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidItemException(String message) {
        super(message);
    }
}
