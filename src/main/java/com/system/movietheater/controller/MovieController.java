package com.system.movietheater.controller;

import com.system.movietheater.domain.movie.*;
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

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterMovie data, UriComponentsBuilder uriBuilder) {
        var movie = new Movie(data);

        movieRepository.save(movie);

        //todo verificar se o filme existe
        var uri = uriBuilder.path("cinema/filme/{id}").buildAndExpand(movie.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataListingMovie(movie));
    }

    @GetMapping
    public List<Movie> list(@PageableDefault(size = 10, sort = "titulo") Pageable paginacao){
        return movieRepository.findAll(paginacao).toList();
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdateMovie data) {
        var movie = movieRepository.getReferenceById(data.id());
        movie.atualizaDados(data);

        return ResponseEntity.ok(new DataListingMovie(movie));
    }
}
