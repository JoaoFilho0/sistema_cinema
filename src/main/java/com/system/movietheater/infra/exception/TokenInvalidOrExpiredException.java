package com.system.movietheater.infra.exception;

public class TokenInvalidOrExpiredException extends RuntimeException {
    public TokenInvalidOrExpiredException(String message) {
        super(message);
    }
}
