package com.splenda.epi.entities.exceptions;

import org.springframework.http.HttpStatus;

public class AuditItemNotFoundException extends DefaultException{
    public AuditItemNotFoundException(String msg){
        super(msg, HttpStatus.BAD_REQUEST, "user.not-found");
    }
}
