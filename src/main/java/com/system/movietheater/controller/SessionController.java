package com.system.movietheater.controller;

import com.system.movietheater.domain.session.DataRegisterSession;
import com.system.movietheater.domain.session.DataListingSession;
import com.system.movietheater.domain.session.Session;
import com.system.movietheater.domain.session.SessionRepository;
import com.system.movietheater.domain.sessionmovie.DataRegisterSessionMovie;
import com.system.movietheater.domain.sessionmovie.SessionMovie;
import com.system.movietheater.domain.sessionmovie.SessionMovieRepository;
import com.system.movietheater.domain.sessionroom.DataRegisterSessionRoom;
import com.system.movietheater.domain.sessionroom.SessionRoom;
import com.system.movietheater.domain.sessionroom.SessionRoomRepository;
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

    @Autowired
    private SessionMovieRepository sessionMovieRepository;

    @Autowired
    private SessionRoomRepository sessionRoomRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterSession data, UriComponentsBuilder uriBuilder) {
        var session = new Session(data.session());

        var sessionData = sessionRepository.save(session);

        //todo verificar se a sessao existe
        sessionMovieRepository.save(new SessionMovie(new DataRegisterSessionMovie(sessionData.getId(), data.movie())));
        sessionRoomRepository.save(new SessionRoom(new DataRegisterSessionRoom(sessionData.getId(), data.room())));

        //todo verificar se a sessão existe
        var uri = uriBuilder.path("cinema/sessao/{id}").buildAndExpand(sessionData.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataListingSession(sessionData));
    }

}
