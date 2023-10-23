package com.system.movietheater.application.controller;

import com.system.movietheater.application.dto.user.DetailingUserDto;
import com.system.movietheater.application.dto.user.ListingUserDto;
import com.system.movietheater.application.dto.user.RegisterUserDto;
import com.system.movietheater.application.dto.user.UpdateUserDto;
import com.system.movietheater.domain.model.User;
import com.system.movietheater.application.dto.session.ListingSessionDto;
import com.system.movietheater.domain.model.Session;
import com.system.movietheater.infrastructure.persistence.repository.SessionRepository;
import com.system.movietheater.application.dto.usersession.RegisterUserSessionDto;
import com.system.movietheater.application.dto.usersession.ReturnUserSessionDto;
import com.system.movietheater.infrastructure.persistence.repository.UserSessionRepository;
import com.system.movietheater.usercase.usersession.UserSessionService;
import com.system.movietheater.usercase.user.UserService;
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
            @ApiResponse(responseCode = "201", description = "Successful request: User saved.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Name, email or/and password is blank." +
                    "<br>Email already exists in database.", content = @Content),
    })
    public ResponseEntity<DetailingUserDto> register(@RequestBody @Valid RegisterUserDto data, UriComponentsBuilder uriBuilder) {
        var user = userService.register(data);

        return ResponseEntity.created(userService.generateUri(user, uriBuilder)).body(new DetailingUserDto(user));
    }

    @PostMapping("/sessao")
    @Transactional
    @Operation(summary = "Save user session")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful request: User session saved.", content = @Content),
            @ApiResponse(responseCode = "400", description = "User or/and session is null.", content = @Content),
            @ApiResponse(responseCode = "404", description = "User or/and session not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<ReturnUserSessionDto> registerUserSession(@RequestBody @Valid RegisterUserSessionDto data, UriComponentsBuilder uriBuilder) {
        var userSession = userSessionService.registerUserSession(data);

        return ResponseEntity.created(userSessionService.generateUri(userSession, uriBuilder)).body(new ReturnUserSessionDto(new ListingUserDto(userSession.getUser()), new ListingSessionDto(userSession.getSession())));
    }

    @GetMapping("{id}/sessao")
    @Operation(summary = "List sessions by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: User session listed.", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<List<Session>> listUserSessionByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(userSessionService.listingUserSessionPerUser(id).getSession());
    }

    @GetMapping
    @Operation(summary = "List users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: User listed.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<List<ListingUserDto>> list(@PageableDefault(size = 10, sort = "name") Pageable pageable) {
         return ResponseEntity.ok(userService.listUsers(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Select user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: User selected.", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DetailingUserDto> select(@PathVariable Long id) {
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
    public ResponseEntity<DetailingUserDto> update(@RequestBody @Valid UpdateUserDto data) {
        return ResponseEntity.ok(new DetailingUserDto(userService.updateUser(data)));
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
    public ResponseEntity<DetailingUserDto> disableAccount(@RequestBody Long id) {
        return ResponseEntity.ok(new DetailingUserDto(userService.disableAccount(id)));
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
    public ResponseEntity<DetailingUserDto> activeAccount(@RequestBody Long id) {
        return ResponseEntity.ok(new DetailingUserDto(userService.activeAccount(id)));
    }

}
