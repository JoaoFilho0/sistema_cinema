package com.system.movietheater.domain.room;

import com.system.movietheater.domain.movietheater.MovieTheater;
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

        movieTheaterRepository.findById(data.movieTheater().id()).orElseThrow(MovieTheaterNotFoundException::new);

        var roomExistis = roomRepository.findByNumberAndMovieTheater(data.number(), new MovieTheater(data.movieTheater()));
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
        System.out.println(data.toString());

        var room = roomRepository.findById(data.id()).orElseThrow(RoomNotFoundException::new);

        movieTheaterRepository.findById(data.movieTheater().id()).orElseThrow(MovieTheaterNotFoundException::new);

        var roomExistis = roomRepository.findByNumberAndSeatsAndMovieTheater(data.number(), data.seats(), new MovieTheater(data.movieTheater()));
        if (roomExistis != null) throw new RoomAlreadyExistsException("Room already exists");

        room.updateData(data);

        return room;
    }

    public void deleteRoom(Long id) {
        var room = roomRepository.findById(id).orElseThrow(RoomNotFoundException::new);

        roomRepository.delete(room);
    }
}
