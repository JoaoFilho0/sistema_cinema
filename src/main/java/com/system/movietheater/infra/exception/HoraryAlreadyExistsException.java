package com.system.movietheater.infra.exception;

public class HoraryAlreadyExistsException extends RuntimeException {

    public HoraryAlreadyExistsException() {
        super("Time already registered for this cinema");
    }
}
