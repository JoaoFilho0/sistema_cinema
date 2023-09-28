package com.system.movietheater.domain.room;

import com.system.movietheater.domain.movietheater.MovieTheaterRepository;
import com.system.movietheater.infra.exception.MovieTheaterNotFoundException;
import com.system.movietheater.infra.exception.RoomAlreadyExistsException;
import com.system.movietheater.infra.exception.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private MovieTheaterRepository movieTheaterRepository;

    public Room registerRoom(DataRegisterRoom data) {
        var room = new Room(data);

        movieTheaterRepository.findById(data.movieTheater().getId()).orElseThrow(MovieTheaterNotFoundException::new);

        var roomExistis = roomRepository.findByNumberAndMovieTheater(data.number(),data.movieTheater());
        if (roomExistis != null) throw new RoomAlreadyExistsException("Room already exists");

        roomRepository.save(room);

        return room;
    }

    public URI generateUri(Room room, UriComponentsBuilder uriBuilder) {
        return uriBuilder.path("usuario/{id}").buildAndExpand(room.getId()).toUri();
    }

    public List<Room> listRooms(Long id) {
        var movieTheater = movieTheaterRepository.findById(id).orElseThrow(MovieTheaterNotFoundException::new);

        return movieTheater.getRooms();
    }

    public Room updateRoom(DataUpdateRoom data) {
        var room = roomRepository.findById(data.id()).orElseThrow(RoomNotFoundException::new);
        room.updateData(data);

        return room;
    }

    public void deleteRoom(Long id) {
        var room = roomRepository.findById(id).orElseThrow(RoomNotFoundException::new);

        roomRepository.delete(room);
    }
}
