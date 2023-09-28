package com.system.movietheater.controller;

import com.system.movietheater.domain.session.DataListingSession;
import com.system.movietheater.domain.session.Session;
import com.system.movietheater.domain.session.SessionRepository;
import com.system.movietheater.domain.user.*;
import com.system.movietheater.domain.usersession.DataRegisterUserSession;
import com.system.movietheater.domain.usersession.DataReturnUserSession;
import com.system.movietheater.domain.usersession.UserSessionRepository;
import com.system.movietheater.domain.usersession.UserSessionService;
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
    @Operation(summary = "Save user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful request: User saved."),
            @ApiResponse(responseCode = "400", description = "Name, email or password is blank."),
    })
    public ResponseEntity<DataDetailingUser> register(@RequestBody @Valid DataRegisterUser data, UriComponentsBuilder uriBuilder) {
        var user = new User(userService.register(data));

        return ResponseEntity.created(userService.generateUri(user, uriBuilder)).body(new DataDetailingUser(user));
    }

    @PostMapping("/sessao")
    @Transactional
    @Operation(summary = "Save user session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful request: User session saved.", content = @Content),
            @ApiResponse(responseCode = "400", description = "User or session is null.", content = @Content),
            @ApiResponse(responseCode = "404", description = "User or session not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DataReturnUserSession> registerUserSession(@RequestBody @Valid DataRegisterUserSession data, UriComponentsBuilder uriBuilder) {
        var userSession = userSessionService.registerUserSession(data);

        return ResponseEntity.created(userSessionService.generateUri(userSession, uriBuilder)).body(new DataReturnUserSession(new DataListingUser(userSession.getUser()), new DataListingSession(userSession.getSession())));
    }

    @GetMapping("{id}/sessao")
    @Operation(summary = "List sessions by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: User session list.", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<List<Session>> listUserSessionByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(userSessionService.listingUserSessionPerUser(id).getSession());
    }

    @GetMapping
    @Operation(summary = "List users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: User list.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<List<DataListingUser>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
         return ResponseEntity.ok(userService.listUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Select user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: User select.", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DataDetailingUser> select(@PathVariable Long id) {
        return ResponseEntity.ok(userService.selectUser(id));
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: User update.", content = @Content),
            @ApiResponse(responseCode = "400", description = "User id is null", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DataDetailingUser> update(@RequestBody @Valid DataUpdateUser data) {
        return ResponseEntity.ok(new DataDetailingUser(userService.updateUser(data)));
    }

    @PutMapping("/desativar")
    @Transactional
    @Operation(summary = "Disable user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful request: User account disable.", content = @Content),
            @ApiResponse(responseCode = "400", description = "User account is disable.", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DataDetailingUser> disableAccount(@RequestBody Long id) {
        return ResponseEntity.ok(new DataDetailingUser(userService.disableAccount(id)));
    }

    @PutMapping("/ativar")
    @Transactional
    @Operation(summary = "Active user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful request: User account active.", content = @Content),
            @ApiResponse(responseCode = "400", description = "User account is active.", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DataDetailingUser> activeAccount(@RequestBody Long id) {
        return ResponseEntity.ok(new DataDetailingUser(userService.activeAccount(id)));
    }

}
