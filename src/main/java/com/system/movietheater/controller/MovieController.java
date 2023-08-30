package com.system.movietheater.controller;

import com.sistema.cinema.domain.movie.*;
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
    public ResponseEntity cadastrar(@RequestBody @Valid DataRegisterMovie dados, UriComponentsBuilder uriBuilder) {
        var filme = new Movie(dados);

        movieRepository.save(filme);

        //todo verificar se o filme existe
        var uri = uriBuilder.path("cinema/filme/{id}").buildAndExpand(filme.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataListingMovie(filme));
    }

    @GetMapping
    public List<Movie> listar(@PageableDefault(size = 10, sort = "titulo") Pageable paginacao){
        return movieRepository.findAll(paginacao).toList();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DataUpdateMovie dados) {
        var filme = movieRepository.getReferenceById(dados.id());
        filme.atualizaDados(dados);

        return ResponseEntity.ok(new DataListingMovie(filme));
    }
}
