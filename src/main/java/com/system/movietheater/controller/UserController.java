package com.system.movietheater.controller;

import com.system.movietheater.domain.movietheater.MovieTheaterRepository;
import com.system.movietheater.domain.session.DataUpdateSession;
import com.system.movietheater.domain.session.SessionRepository;
import com.system.movietheater.domain.user.*;
import com.system.movietheater.domain.usersession.DataRegisterUserSession;
import com.system.movietheater.domain.usersession.UserSession;
import com.system.movietheater.domain.usersession.UserSessionRepository;
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
@RequestMapping("usuario")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieTheaterRepository movieTheaterRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterUser data, UriComponentsBuilder uriBuilder) {
        var user = new User(data);

        userRepository.save(user);

        var uri = uriBuilder.path("usuario/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailingUser(user));
    }

    @PostMapping("/sessao")
    @Transactional
    public void registerUserSession(@RequestBody @Valid DataRegisterUserSession data, UriComponentsBuilder uriBuilder) {
        var userSession = new UserSession(data);
        var session = sessionRepository.getReferenceById(data.session().id());

        userSession.setSession(session);

        userSessionRepository.save(userSession);

        session.setIngressos(session.getIngressos() - 1);
        session.atualizaDados(new DataUpdateSession(session.getId(), session.getIngressos(), session.getPreco()));

    }

    @GetMapping
    public List<User> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User select(@PathVariable Long id) {
        var page = userRepository.findById(id);
        return page.get();
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdateUser data) {
        var user = userRepository.getReferenceById(data.id());
        user.atualizaDados(data);

        return ResponseEntity.ok(new DataDetailingUser(user));
    }
}
