package com.system.movietheater.domain.room;

public record DataListingRoom(Long id, int numero, int assentos) {

    public DataListingRoom(Room room) {
        this(room.getId(), room.getNumber(), room.getSeats());
    }

}
