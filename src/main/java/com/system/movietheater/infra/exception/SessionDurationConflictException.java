package com.system.movietheater.infra.exception;

public class SessionDurationConflictException extends RuntimeException{

    public SessionDurationConflictException() {
        super("There is already a session in the meantime");
    }
}
