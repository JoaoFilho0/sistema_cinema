package com.system.movietheater.infra.exception;

public class SessionDayInvalidException extends RuntimeException {

    public SessionDayInvalidException () {
        super("Cinema closed on the day");
    }
}
