package com.system.movietheater.infra.exception;

public class MovieAlreadyExistsException extends RuntimeException {

    public MovieAlreadyExistsException() {
        super("Movie already exists");
    }
}
