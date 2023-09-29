package com.system.movietheater.infra.exception;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException () {
        super("Address not found");
    }
}
