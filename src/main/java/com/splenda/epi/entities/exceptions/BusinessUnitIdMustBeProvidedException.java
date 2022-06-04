package com.splenda.epi.entities.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessUnitIdMustBeProvidedException extends DefaultException {
    public BusinessUnitIdMustBeProvidedException(String msg){
        super(msg, HttpStatus.BAD_REQUEST, "business-unit.id-must-be-provided");
    }

}
