package com.sistema.cinema.controller;

import com.sistema.cinema.domain.cliente.DadosListagemCliente;
import com.sistema.cinema.domain.sala.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cinema/sala")
public class SalaController {

    @Autowired
    private SalaRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroSala dados) {
        var sala = new Sala(dados);
        repository.save(sala);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemSala>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemSala::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizaSala dados) {
        var sala = repository.getReferenceById(dados.id());
        sala.atualizaDados(dados);
    }

}
