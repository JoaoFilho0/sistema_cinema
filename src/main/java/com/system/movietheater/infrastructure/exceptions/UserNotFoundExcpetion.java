package com.system.movietheater.infrastructure.exceptions;

public class UserNotFoundExcpetion extends RuntimeException{

    public UserNotFoundExcpetion() {
        super("User not found");
    }
}
