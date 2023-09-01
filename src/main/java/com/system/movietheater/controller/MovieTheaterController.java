package com.system.movietheater.controller;

import com.system.movietheater.domain.movietheater.*;
import com.system.movietheater.domain.user.UserRepository;
import com.system.movietheater.domain.address.AddressRepository;
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
    private MovieTheaterRepository movieTheaterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterMovieTheater data, UriComponentsBuilder uriBuilder) {
        var movieTheater = new MovieTheater(data);
        var user = userRepository.getReferenceById(data.user());

        //todo verificar se cliente existe
        var movieTheaterData = movieTheaterRepository.save(movieTheater);
        user.setMovieTheater(movieTheaterData);

        userRepository.save(user);

        //todo retornar entidade salva
        var uri = uriBuilder.path("cinema/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetalingMovieTheater(movieTheaterData));
    }

    @GetMapping
    public ResponseEntity<List<DataListingMovieTheater>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = movieTheaterRepository.findAll(paginacao).map(DataListingMovieTheater::new).toList();
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DataUpdateMovieTheater data) {
        var movieTheater = movieTheaterRepository.getReferenceById(data.id());
        movieTheater.atualizaDados(data);

        return ResponseEntity.ok(new DataDetalingMovieTheater(movieTheater));
    }
}
