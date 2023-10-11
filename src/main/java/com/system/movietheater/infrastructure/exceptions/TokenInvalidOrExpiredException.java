package com.system.movietheater.infrastructure.exceptions;

public class TokenInvalidOrExpiredException extends RuntimeException {
    public TokenInvalidOrExpiredException(String message) {
        super(message);
    }
}
