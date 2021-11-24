package com.switchfullywork.eurder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserAllreadyExistsException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "This emailaddress is allready registered.";

    public UserAllreadyExistsException() {
        super(DEFAULT_MESSAGE);
    }
}
