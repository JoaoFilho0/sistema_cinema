package com.system.movietheater.infrastructure.exceptions;

public class AddressMovieTheaterAlreadyExistsException extends RuntimeException {
    public AddressMovieTheaterAlreadyExistsException(String message) {
        super(message);
    }
}
