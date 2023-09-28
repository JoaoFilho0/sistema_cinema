package com.system.movietheater.controller;

import com.system.movietheater.domain.movie.*;
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
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DataListingMovie> register(@RequestBody @Valid DataRegisterMovie data, UriComponentsBuilder uriBuilder) {
        var movie = new Movie(movieService.registerMovie(data));

        return ResponseEntity.created(movieService.generateUri(movie, uriBuilder)).body(new DataListingMovie(movie));
    }

    @GetMapping
    public ResponseEntity<List<Movie>> list(@PageableDefault(size = 10, sort = "title") Pageable pagination){
        return ResponseEntity.ok(movieService.listMovies(pagination));
    }

    @PutMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<DataListingMovie> update(@RequestBody @Valid DataUpdateMovie data) {
        return ResponseEntity.ok(new DataListingMovie(movieService.updateMovie(data)));
    }
}
