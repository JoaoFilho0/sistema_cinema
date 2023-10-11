package com.system.movietheater.infrastructure.exceptions;

public class SessionDurationInvalidException extends RuntimeException {

    public SessionDurationInvalidException() {
        super("Session duration is invalid");
    }
}
