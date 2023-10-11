package com.system.movietheater.infrastructure.exceptions;

public class MovieAlreadyExistsException extends RuntimeException {

    public MovieAlreadyExistsException() {
        super("Movie already exists");
    }
}
