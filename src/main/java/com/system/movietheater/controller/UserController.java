package com.system.movietheater.controller;

import com.system.movietheater.domain.movietheater.MovieTheaterRepository;
import com.system.movietheater.domain.session.DataUpdateSession;
import com.system.movietheater.domain.session.SessionRepository;
import com.sistema.cinema.domain.user.*;
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
    public ResponseEntity cadastrar(@RequestBody @Valid DataRegisterUser dados, UriComponentsBuilder uriBuilder) {
        var usuario = new User(dados);
        userRepository.save(usuario);

        var uri = uriBuilder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailingUser(usuario));
    }

    @PostMapping("/sessao")
    @Transactional
    public void cadastrarUsuarioSessao(@RequestBody @Valid DataRegisterUserSession dados, UriComponentsBuilder uriBuilder) {
        var usuarioSessao = new UserSession(dados);
        var sessao = sessionRepository.getReferenceById(dados.sessao().id());

        usuarioSessao.setSession(sessao);

        userSessionRepository.save(usuarioSessao);

        sessao.setIngressos(sessao.getIngressos() - 1);
        sessao.atualizaDados(new DataUpdateSession(sessao.getId(), sessao.getIngressos(), sessao.getPreco()));

    }

    @GetMapping
    public List<User> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User selecionar(@PathVariable Long id) {
        var page = userRepository.findById(id);
        return page.get();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DataUpdateUser dados) {
        var usuario = userRepository.getReferenceById(dados.id());
        usuario.atualizaDados(dados);

        return ResponseEntity.ok(new DataDetailingUser(usuario));
    }
}
