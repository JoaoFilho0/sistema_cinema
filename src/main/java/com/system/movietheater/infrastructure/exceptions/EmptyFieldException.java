package com.system.movietheater.infrastructure.exceptions;

public class EmptyFieldException extends RuntimeException {
    public EmptyFieldException(String message) {
        super(message);
    }
}
