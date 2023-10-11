package com.system.movietheater.infrastructure.exceptions;

public class SessionDayInvalidException extends RuntimeException {

    public SessionDayInvalidException () {
        super("Cinema closed on the day");
    }
}
