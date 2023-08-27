package com.sistema.cinema.controller;

import com.sistema.cinema.domain.cinema.*;
import com.sistema.cinema.domain.cliente.DadosListagemCliente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cinema")
public class CinemaController {

    @Autowired
    private CinemaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCinema dados, UriComponentsBuilder uriBuilder) {
        var cinema = new Cinema(dados);
        repository.save(cinema);

        var uri =  uriBuilder.path("cinema/{id}").buildAndExpand(cinema.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCinema(cinema));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCinema>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemCinema::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaCinema dados) {
        var cinema = repository.getReferenceById(dados.id());
        cinema.atualizaDados(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCinema(cinema));
    }
}
