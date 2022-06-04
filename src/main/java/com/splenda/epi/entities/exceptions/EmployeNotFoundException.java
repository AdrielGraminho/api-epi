package com.splenda.epi.entities.exceptions;

import org.springframework.http.HttpStatus;

public class EmployeNotFoundException extends DefaultException {
    public EmployeNotFoundException(String msg){
        super(msg, HttpStatus.NOT_FOUND, "employee.not-found");
    }
}
