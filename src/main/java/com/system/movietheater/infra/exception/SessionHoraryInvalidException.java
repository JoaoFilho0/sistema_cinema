package com.system.movietheater.infra.exception;

public class SessionHoraryInvalidException extends RuntimeException {

    public SessionHoraryInvalidException() {
        super("Outside cinema opening hours");
    }
}
