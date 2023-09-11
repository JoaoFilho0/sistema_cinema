package com.system.movietheater.controller;

import com.system.movietheater.domain.session.DataRegisterSession;
import com.system.movietheater.domain.session.DataListingSession;
import com.system.movietheater.domain.session.Session;
import com.system.movietheater.domain.session.SessionRepository;
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
    private SessionRepository sessionRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterSession data, UriComponentsBuilder uriBuilder) {
        var session = new Session(data.session());

        var sessionData = sessionRepository.save(session);

        //todo verificar se a sessão existe
        var uri = uriBuilder.path("cinema/sessao/{id}").buildAndExpand(sessionData.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataListingSession(sessionData));
    }

}
