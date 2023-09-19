package com.system.movietheater.infra.exception;

public class UserNotFoundExcpetion extends RuntimeException{

    public UserNotFoundExcpetion(String message) {
        super(message);
    }
}
