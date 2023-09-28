package com.system.movietheater.infra.exception;

public class MovieTheaterNotFoundException extends RuntimeException {

    public MovieTheaterNotFoundException() {
        super("Movie theater not found");
    }
}
