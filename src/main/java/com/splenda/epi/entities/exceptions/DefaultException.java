package com.splenda.epi.entities.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class DefaultException extends RuntimeException {

    private HttpStatus status;

    private String key;

    private Map<String, Object> params;

    public DefaultException(String message, HttpStatus status, String key, Map<String, Object> params) {
        super(message);
        this.status = status;
        this.key = key;
        this.params = params;
    }

    public DefaultException(String message, HttpStatus status, String key, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.key = key;
    }

    public DefaultException(String message, HttpStatus status, String key) {
        super(message);
        this.status = status;
        this.key = key;
    }

    public DefaultException(HttpStatus status, String key) {
        this.status = status;
        this.key = key;
    }

    public DefaultException(HttpStatus status, String key, Map<String, Object> params) {
        this.status = status;
        this.key = key;
        this.params = params;
    }

}