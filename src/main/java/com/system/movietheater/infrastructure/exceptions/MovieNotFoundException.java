package com.system.movietheater.infrastructure.exceptions;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException () {
        super("Movie not found");
    }
}
