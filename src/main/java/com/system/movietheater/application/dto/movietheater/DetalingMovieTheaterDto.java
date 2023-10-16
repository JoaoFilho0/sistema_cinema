package com.system.movietheater.application.dto.movietheater;

import com.system.movietheater.domain.model.Address;
import com.system.movietheater.domain.model.Horary;
import com.system.movietheater.domain.model.MovieTheater;
import com.system.movietheater.domain.model.Room;

import java.util.List;

public record DetalingMovieTheaterDto(
        Long id,
        String name,
        Address address,
        List<Room> rooms,
        List<Horary> horaries
) {

    public DetalingMovieTheaterDto(MovieTheater movieTheater) {
        this(movieTheater.getId(), movieTheater.getName(), movieTheater.getAddress(), movieTheater.getRooms(), movieTheater.getHoraries());
    }

}
