package com.system.movietheater.infrastructure.exceptions;

public class NameMovieTheaterAlreadyRegisteredException extends RuntimeException{

    public NameMovieTheaterAlreadyRegisteredException(String message) {
        super(message);
    }
}
