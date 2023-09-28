package com.system.movietheater.infra.exception;

public class HoraryNotFoundException extends RuntimeException {

    public HoraryNotFoundException(){
        super("Horary not found.");
    }
}
