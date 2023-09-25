package com.system.movietheater.infra.exception;

public class NameMovieTheaterAlreadyRegisteredException extends RuntimeException{

    public NameMovieTheaterAlreadyRegisteredException(String message) {
        super(message);
    }
}
