package com.system.movietheater.application.dto.room;

import com.system.movietheater.domain.model.Room;

public record DataListingRoom(Long id, int numero, int assentos) {

    public DataListingRoom(Room room) {
        this(room.getId(), room.getNumber(), room.getSeats());
    }

}
