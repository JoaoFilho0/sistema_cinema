package com.system.movietheater.application.dto.room;

import com.system.movietheater.domain.model.Room;

public record ListingRoomDto(Long id, int numero, int assentos) {

    public ListingRoomDto(Room room) {
        this(room.getId(), room.getNumber(), room.getSeats());
    }

}
