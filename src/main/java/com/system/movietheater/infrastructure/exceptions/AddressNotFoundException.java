package com.system.movietheater.infrastructure.exceptions;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException () {
        super("Address not found");
    }
}
