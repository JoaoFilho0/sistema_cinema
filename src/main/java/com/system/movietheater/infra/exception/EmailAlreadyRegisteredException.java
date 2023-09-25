package com.system.movietheater.infra.exception;

public class EmailAlreadyRegisteredException extends RuntimeException{

    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
