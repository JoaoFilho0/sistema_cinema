package com.system.movietheater.infrastructure.exceptions;

public class MovieTheaterNotFoundException extends RuntimeException {

    public MovieTheaterNotFoundException() {
        super("Movie theater not found");
    }
}
