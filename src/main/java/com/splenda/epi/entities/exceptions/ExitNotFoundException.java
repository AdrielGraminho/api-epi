package com.splenda.epi.entities.exceptions;

import org.springframework.http.HttpStatus;

public class ExitNotFoundException extends DefaultException {
    public ExitNotFoundException(String msg) {
        super(msg, HttpStatus.NOT_FOUND, "exit.not-found");
    }
}
