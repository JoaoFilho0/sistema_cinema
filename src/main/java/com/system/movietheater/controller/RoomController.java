package com.system.movietheater.controller;

import com.system.movietheater.domain.movietheater.MovieTheaterRepository;
import com.system.movietheater.domain.movietheaterroom.MovieTheaterRoomRepository;
import com.system.movietheater.domain.movietheaterroom.MovieTheaterRoom;
import com.system.movietheater.domain.movietheaterroom.DataRegisterMovieTheaterRoom;
import com.system.movietheater.domain.room.DataUpdateRoom;
import com.system.movietheater.domain.room.Room;
import com.system.movietheater.domain.room.RoomRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cinema/sala")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MovieTheaterRepository movieTheaterRepository;

    @Autowired
    private MovieTheaterRoomRepository movieTheaterRoomRepository;

    @PostMapping
    @Transactional
    public void regiter(@RequestBody @Valid DataRegisterMovieTheaterRoom data) {
        var movieTheaterRoom = new MovieTheaterRoom(data);

        movieTheaterRoomRepository.save(movieTheaterRoom);
    }

    @GetMapping("/{movie_theater_id}")
    public List<Room> list(@PathVariable Long movie_theater_id, @PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        //todo verificar se o cinema existe
        var movieTheater = movieTheaterRepository.getReferenceById(movie_theater_id);
        var page = movieTheater.getRoom();

        return page;
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DataUpdateRoom data) {
        //todo verificar se o cinema existe
        var room = roomRepository.getReferenceById(data.id());
        room.updateData(data);
    }

}
