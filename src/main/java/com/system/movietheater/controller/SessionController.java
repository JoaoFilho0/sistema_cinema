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
import com.system.movietheater.domain.sessionroom.SessaoSalasRepository;
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
    private SessaoSalasRepository sessaoSalasRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DataRegisterSession dados, UriComponentsBuilder uriBuilder) {
        var sessao = new Session(dados.sessao());

        var dadosSessao = sessionRepository.save(sessao);

        //todo verificar se a sessao existe
        sessionMovieRepository.save(new SessionMovie(new DataRegisterSessionMovie(dadosSessao.getId(), dados.filme())));
        sessaoSalasRepository.save(new SessionRoom(new DataRegisterSessionRoom(dadosSessao.getId(), dados.sala())));

        //todo verificar se a sessão existe
        var uri = uriBuilder.path("cinema/sessao/{id}").buildAndExpand(sessao.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataListingSession(sessao));
    }

}
