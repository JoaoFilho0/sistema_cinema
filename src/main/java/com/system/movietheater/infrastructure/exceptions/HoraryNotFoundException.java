package com.system.movietheater.infrastructure.exceptions;

public class HoraryNotFoundException extends RuntimeException {

    public HoraryNotFoundException(){
        super("Horary not found.");
    }
}
