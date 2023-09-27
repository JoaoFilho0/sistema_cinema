package com.system.movietheater.infra.exception;

public class SessionDurationInvalidException extends RuntimeException {

    public SessionDurationInvalidException() {
        super("Session duration is invalid");
    }
}
