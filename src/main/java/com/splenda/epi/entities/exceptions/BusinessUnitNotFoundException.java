package com.splenda.epi.entities.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class BusinessUnitNotFoundException extends DefaultException {
    public BusinessUnitNotFoundException(String msg) {
        super(msg, HttpStatus.BAD_REQUEST, "business-unit.not-found");
    }
}
