package com.switchfullywork.eurder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserAllreadyExistsException extends RuntimeException {

    public UserAllreadyExistsException(String message) {
        super(message);
    }
}
