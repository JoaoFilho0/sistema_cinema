package com.system.movietheater.infra.exception;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException () {
        super("Movie not found");
    }
}
