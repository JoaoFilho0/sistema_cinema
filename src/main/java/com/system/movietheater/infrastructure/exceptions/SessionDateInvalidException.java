package com.system.movietheater.infrastructure.exceptions;

public class SessionDateInvalidException extends RuntimeException {

    public SessionDateInvalidException(String message) {
        super(message);
    }
}
