package com.system.movietheater.controller;

import com.system.movietheater.domain.session.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cinema/sessao")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping
    @Transactional
    public ResponseEntity<DataListingSession> register(@RequestBody @Valid DataRegisterSession data, UriComponentsBuilder uriBuilder) {
        var session = new Session(sessionService.registerSession(data));

        return ResponseEntity.created(sessionService.generateUri(session, uriBuilder)).body(new DataListingSession(session));
    }

}
