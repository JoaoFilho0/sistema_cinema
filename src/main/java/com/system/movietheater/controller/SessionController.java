package com.system.movietheater.controller;

import com.system.movietheater.domain.session.DataListingSession;
import com.system.movietheater.domain.session.DataRegisterSession;
import com.system.movietheater.domain.session.Session;
import com.system.movietheater.domain.session.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("cinema/sessao")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping
    @Transactional
    @Operation(summary = "Save session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful request: Session saved.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid time.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Room or movie not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DataListingSession> register(@RequestBody @Valid DataRegisterSession data, UriComponentsBuilder uriBuilder) {
        var session = new Session(sessionService.registerSession(data));

        return ResponseEntity.created(sessionService.generateUri(session, uriBuilder)).body(new DataListingSession(session));
    }

    @GetMapping
    @Operation(summary = "List sessions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: Session listed.", content = @Content),
    })
    public ResponseEntity<List<DataListingSession>> list() {
        return ResponseEntity.ok(sessionService.list());
    }

}
