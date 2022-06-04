package com.splenda.epi.entities.exceptions;

import org.springframework.http.HttpStatus;

public class ReceivedAlreadyExistException extends DefaultException {
    public ReceivedAlreadyExistException(String msg) {
        super(msg, HttpStatus.BAD_REQUEST, "received.already-exists");
    }
}
