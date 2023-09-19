package com.system.movietheater.controller;

import com.system.movietheater.domain.movietheater.*;
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
    public ResponseEntity<DataDetalingMovieTheater> register(@RequestBody @Valid DataRegisterMovieTheater data, UriComponentsBuilder uriBuilder) {
        var movieTheater = new MovieTheater(movieTheaterService.register(data));

        return ResponseEntity.created(movieTheaterService.generateUri(movieTheater, uriBuilder)).body(new DataDetalingMovieTheater(movieTheater));
    }

    @GetMapping
    public ResponseEntity<List<MovieTheater>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable pagination) {
        return ResponseEntity.ok(movieTheaterService.listMovieTheaters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataDetalingMovieTheater> select(@PathVariable Long id) {
        return ResponseEntity.ok(new DataDetalingMovieTheater(movieTheaterService.selectMovieTheater(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataDetalingMovieTheater> update(@RequestBody @Valid DataUpdateMovieTheater data) {
        return ResponseEntity.ok(new DataDetalingMovieTheater(movieTheaterService.updateMovieTheater(data)));
    }
}
