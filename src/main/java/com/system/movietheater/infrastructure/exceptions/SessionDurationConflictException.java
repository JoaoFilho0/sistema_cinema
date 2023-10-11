package com.system.movietheater.infrastructure.exceptions;

public class SessionDurationConflictException extends RuntimeException{

    public SessionDurationConflictException() {
        super("There is already a session in the meantime");
    }
}
