package com.splenda.epi.entities.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class BusinessUnitNotFoundException extends DefaultException {
    public BusinessUnitNotFoundException(String msg) {
        super(msg, HttpStatus.NOT_FOUND, "business-unit.not-found");
    }
}
