package com.system.movietheater.controller;

import com.system.movietheater.domain.session.DataListingSession;
import com.system.movietheater.domain.session.Session;
import com.system.movietheater.domain.session.SessionRepository;
import com.system.movietheater.domain.user.*;
import com.system.movietheater.domain.usersession.DataRegisterUserSession;
import com.system.movietheater.domain.usersession.DataReturnUserSession;
import com.system.movietheater.domain.usersession.UserSessionRepository;
import com.system.movietheater.domain.usersession.UserSessionService;
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
    private SessionRepository sessionRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserSessionService userSessionService;

    @PostMapping
    @Transactional
    public ResponseEntity<DataDetailingUser> register(@RequestBody @Valid DataRegisterUser data, UriComponentsBuilder uriBuilder) {
        var user = new User(userService.register(data));

        return ResponseEntity.created(userService.generateUri(user, uriBuilder)).body(new DataDetailingUser(user));
    }

    @PostMapping("/sessao")
    @Transactional
    public ResponseEntity<DataReturnUserSession> registerUserSession(@RequestBody @Valid DataRegisterUserSession data, UriComponentsBuilder uriBuilder) {
        var userSession = userSessionService.registerUserSession(data);

        return ResponseEntity.created(userSessionService.generateUri(userSession, uriBuilder)).body(new DataReturnUserSession(new DataListingUser(userSession.getUser()), new DataListingSession(userSession.getSession())));
    }

    @GetMapping("{id}/sessao")
    public ResponseEntity<List<Session>> listUserSessionByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(userSessionService.listingUserSessionPerUser(id).getSession());
    }

    @GetMapping
    public ResponseEntity<List<DataListingUser>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
         return ResponseEntity.ok(userService.listUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetailingUser> select(@PathVariable Long id) {
        return ResponseEntity.ok(userService.selectUser(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataDetailingUser> update(@RequestBody @Valid DataUpdateUser data) {
        return ResponseEntity.ok(new DataDetailingUser(userService.updateUser(data)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DataDetailingUser> disableAccount(@PathVariable Long id) {
        return ResponseEntity.ok(new DataDetailingUser(userService.disableAccount(id)));
    }

    @PutMapping("/ativar/{id}")
    @Transactional
    public ResponseEntity<DataDetailingUser> activeAccount(@PathVariable Long id) {
        return ResponseEntity.ok(new DataDetailingUser(userService.activeAccount(id)));
    }

}
