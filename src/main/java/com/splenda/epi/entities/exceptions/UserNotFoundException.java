package com.splenda.epi.entities.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends DefaultException{
    public UserNotFoundException(String msg) {
        super(msg, HttpStatus.BAD_REQUEST, "user.not-found");
    }
}
