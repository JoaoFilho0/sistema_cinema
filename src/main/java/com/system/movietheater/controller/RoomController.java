package com.system.movietheater.controller;

import com.system.movietheater.domain.movietheater.MovieTheaterRepository;
import com.system.movietheater.domain.room.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("cinema/sala")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MovieTheaterRepository movieTheaterRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterRoom data, UriComponentsBuilder uriBuilder) {
        var room = new Room(data);

        roomRepository.save(room);

        var uri = uriBuilder.path("usuario/{id}").buildAndExpand(room.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataListingRoom(room));
    }

    @GetMapping("/{movie_theater_id}")
    public ResponseEntity<List<Room>> list(@PathVariable Long movie_theater_id, @PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        var movieTheater = movieTheaterRepository.findById(movie_theater_id).orElseThrow(() -> new EntityNotFoundException("Movie Theater not found"));
        var page = movieTheater.getRooms();

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DataUpdateRoom data) {
        //todo verificar se o cinema existe
        var room = roomRepository.getReferenceById(data.id());
        room.updateData(data);
    }

}
