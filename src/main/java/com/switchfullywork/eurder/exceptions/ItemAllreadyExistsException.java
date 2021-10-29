package com.switchfullywork.eurder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ItemAllreadyExistsException extends RuntimeException {

    public ItemAllreadyExistsException(String message) {
        super(message);
    }
}
