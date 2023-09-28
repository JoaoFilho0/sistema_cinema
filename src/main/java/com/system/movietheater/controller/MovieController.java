package com.system.movietheater.controller;

import com.system.movietheater.domain.movie.*;
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
@RequestMapping("cinema/filme")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    @Transactional
    @Operation(summary = "Save movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful request: Movie saved.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Title or/and duration is null." +
                    "<br> Movie already exists in database.", content = @Content)
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DataListingMovie> register(@RequestBody @Valid DataRegisterMovie data, UriComponentsBuilder uriBuilder) {
        var movie = new Movie(movieService.registerMovie(data));

        return ResponseEntity.created(movieService.generateUri(movie, uriBuilder)).body(new DataListingMovie(movie));
    }

    @GetMapping
    @Operation(summary = "List movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: Movie listed.", content = @Content)
    })
    public ResponseEntity<List<DataListingMovie>> list(@PageableDefault(size = 10, sort = "title") Pageable pagination){
        return ResponseEntity.ok(movieService.listMovies(pagination));
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Update movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: Movie updated.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Title or/and duration is null." +
                    "<br> Movie already exists in database.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie not found.", content = @Content)
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DataListingMovie> update(@RequestBody @Valid DataUpdateMovie data) {
        return ResponseEntity.ok(new DataListingMovie(movieService.updateMovie(data)));
    }
}
