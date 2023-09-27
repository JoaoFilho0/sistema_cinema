package com.system.movietheater.infra.exception;

public class SessionDateInvalidException extends RuntimeException {

    public SessionDateInvalidException(String message) {
        super(message);
    }
}
