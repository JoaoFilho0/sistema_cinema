package com.system.movietheater.infra.exception;

public class RoomNotFoundException extends RuntimeException{

    public RoomNotFoundException () {
        super("Room not found");
    }
}
