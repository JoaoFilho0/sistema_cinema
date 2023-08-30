package com.system.movietheater.controller;

import com.sistema.cinema.domain.movietheater.*;
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
    public ResponseEntity cadastrar(@RequestBody @Valid DataRegisterMovieTheater dados, UriComponentsBuilder uriBuilder) {
        var cinema = new MovieTheater(dados);
        var cliente = userRepository.getReferenceById(dados.cliente());

        //todo verificar se cliente existe
        var cinemaDados = movieTheaterRepository.save(cinema);
        cliente.setMovieTheater(cinemaDados);

        userRepository.save(cliente);

        //todo retornar entidade salva
        var uri = uriBuilder.path("cinema/{id}").buildAndExpand(cinema.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetalingMovieTheater(cinema));
    }

    @GetMapping
    public ResponseEntity<List<DataListingMovieTheater>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = movieTheaterRepository.findAll(paginacao).map(DataListingMovieTheater::new).toList();
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DataUpdateMovieTheater dados) {
        var cinema = movieTheaterRepository.getReferenceById(dados.id());
        cinema.atualizaDados(dados);

        return ResponseEntity.ok(new DataDetalingMovieTheater(cinema));
    }
}
