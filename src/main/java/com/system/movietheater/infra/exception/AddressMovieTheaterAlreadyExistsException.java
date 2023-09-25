package com.system.movietheater.infra.exception;

public class AddressMovieTheaterAlreadyExistsException extends RuntimeException {
    public AddressMovieTheaterAlreadyExistsException(String message) {
        super(message);
    }
}
