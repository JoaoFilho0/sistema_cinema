package com.system.movietheater.controller;

import com.system.movietheater.domain.room.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    @Operation(summary = "Save room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful request: Room saved.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Number, seats or/and movie theater is null." +
                    "<br> The room already exists in the cinema.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie theater not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DataListingRoom> register(@RequestBody @Valid DataRegisterRoom data, UriComponentsBuilder uriBuilder) {
        var room = new Room(roomService.registerRoom(data));

        return ResponseEntity.created(roomService.generateUri(room, uriBuilder)).body(new DataListingRoom(room));
    }

    @GetMapping("/{movie_theater_id}")
    @Operation(summary = "List room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: Room listed.", content = @Content)
    })
    public ResponseEntity<List<Room>> list(@PathVariable Long movie_theater_id, @PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        return ResponseEntity.ok(roomService.listRooms(movie_theater_id));
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Update room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: Room updated.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Id room is null.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Room not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Room> update(@RequestBody @Valid DataUpdateRoom data) {
        return ResponseEntity.ok(roomService.updateRoom(data));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful request: Room deleted.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Room not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        roomService.deleteRoom(id);

        return ResponseEntity.noContent().build();
    }
}
