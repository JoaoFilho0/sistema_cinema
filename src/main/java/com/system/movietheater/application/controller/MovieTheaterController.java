package com.system.movietheater.application.controller;

import com.system.movietheater.application.dto.movietheater.DetalingMovieTheaterDto;
import com.system.movietheater.application.dto.movietheater.ListingMovieTheaterDto;
import com.system.movietheater.application.dto.movietheater.RegisterMovieTheaterDto;
import com.system.movietheater.application.dto.movietheater.UpdateMovieTheaterDto;
import com.system.movietheater.domain.model.MovieTheater;
import com.system.movietheater.usercase.movietheater.MovieTheaterService;
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
@RequestMapping("cinema")
public class MovieTheaterController {

    @Autowired
    private MovieTheaterService movieTheaterService;

    @PostMapping
    @Transactional
    @Operation(summary = "Save movie theater")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful request: Movie theater saved.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Name of the cinema already in use." +
                    "<br>Cinema address already in use.", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DetalingMovieTheaterDto> register(@RequestBody @Valid RegisterMovieTheaterDto data, UriComponentsBuilder uriBuilder) {
        var movieTheater = new MovieTheater(movieTheaterService.register(data));

        return ResponseEntity.created(movieTheaterService.generateUri(movieTheater, uriBuilder)).body(new DetalingMovieTheaterDto(movieTheater));
    }

    @GetMapping
    @Operation(summary = "List movie theaters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: List movie theaters.", content = @Content),
    })
    public ResponseEntity<List<ListingMovieTheaterDto>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable pagination) {
        return ResponseEntity.ok(movieTheaterService.listMovieTheaters());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Select movie theater")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: Movie theater selected.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie theater not found.", content = @Content),
    })
    public ResponseEntity<DetalingMovieTheaterDto> select(@PathVariable Long id) {
        return ResponseEntity.ok(new DetalingMovieTheaterDto(movieTheaterService.selectMovieTheater(id)));
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Update movie theater")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: Movie theater updated.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie theater not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DetalingMovieTheaterDto> update(@RequestBody @Valid UpdateMovieTheaterDto data) {
        return ResponseEntity.ok(new DetalingMovieTheaterDto(movieTheaterService.updateMovieTheater(data)));
    }

    @DeleteMapping
    @Operation(summary = "Delete movie theater")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful request: Movie theater deleted.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie theater not found." +
                    "<br>Address not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<String> delete(@RequestBody Long id) {
        movieTheaterService.deleteMovieTheater(id);

        return ResponseEntity.noContent().build();
    }
}
