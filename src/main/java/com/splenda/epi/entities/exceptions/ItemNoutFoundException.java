package com.splenda.epi.entities.exceptions;

import org.springframework.http.HttpStatus;

public class ItemNoutFoundException extends DefaultException {
    public ItemNoutFoundException(String msg) {
        super(msg, HttpStatus.BAD_REQUEST, "item.not-found");
    }
}
