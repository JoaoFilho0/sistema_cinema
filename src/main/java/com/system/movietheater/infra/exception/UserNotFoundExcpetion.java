package com.system.movietheater.infra.exception;

public class UserNotFoundExcpetion extends RuntimeException{

    public UserNotFoundExcpetion() {
        super("User not found");
    }
}
