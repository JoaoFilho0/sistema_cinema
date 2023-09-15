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
    private RoomService roomService;

    @PostMapping
    @Transactional
    public ResponseEntity<DataListingRoom> register(@RequestBody @Valid DataRegisterRoom data, UriComponentsBuilder uriBuilder) {
        var room = new Room(roomService.registerRoom(data));

        return ResponseEntity.created(roomService.generateUri(room, uriBuilder)).body(new DataListingRoom(room));
    }

    @GetMapping("/{movie_theater_id}")
    public ResponseEntity<List<Room>> list(@PathVariable Long movie_theater_id, @PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        return ResponseEntity.ok(roomService.listRooms(movie_theater_id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Room> update(@RequestBody @Valid DataUpdateRoom data) {
        return ResponseEntity.ok(roomService.updateRoom(data));
    }

}
