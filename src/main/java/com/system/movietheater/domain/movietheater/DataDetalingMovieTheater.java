package com.system.movietheater.domain.movietheater;

import com.system.movietheater.domain.address.Address;
import com.system.movietheater.domain.horary.Horary;
import com.system.movietheater.domain.room.Room;

import java.util.List;

public record DataDetalingMovieTheater(
        Long id,
        String name,
        Address address,
        List<Room> rooms,
        List<Horary> horaries
) {

    public DataDetalingMovieTheater(MovieTheater movieTheater) {
        this(movieTheater.getId(), movieTheater.getName(), movieTheater.getAddress(), movieTheater.getRooms(), movieTheater.getHoraries());
    }

}
