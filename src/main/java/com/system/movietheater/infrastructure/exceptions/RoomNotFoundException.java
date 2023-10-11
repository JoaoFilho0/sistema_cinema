package com.system.movietheater.infrastructure.exceptions;

public class RoomNotFoundException extends RuntimeException{

    public RoomNotFoundException () {
        super("Room not found");
    }
}
