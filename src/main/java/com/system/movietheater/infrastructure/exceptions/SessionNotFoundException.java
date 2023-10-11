package com.system.movietheater.infrastructure.exceptions;

public class SessionNotFoundException extends RuntimeException {

    public SessionNotFoundException() {
        super("Session not found");
    }
}
