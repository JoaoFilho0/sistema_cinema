package com.system.movietheater.infrastructure.exceptions;

public class HoraryAlreadyExistsException extends RuntimeException {

    public HoraryAlreadyExistsException() {
        super("Time already registered for this cinema");
    }
}
