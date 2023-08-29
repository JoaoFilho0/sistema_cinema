package com.sistema.cinema.controller;

import com.sistema.cinema.domain.cinema.DadosAtualizaCinema;
import com.sistema.cinema.domain.cinema.DadosDetalhamentoCinema;
import com.sistema.cinema.domain.filme.*;
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
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroFilme dados, UriComponentsBuilder uriBuilder) {
        var filme = new Filme(dados);

        filmeRepository.save(filme);

        var uri = uriBuilder.path("cinema/filme/{id}").buildAndExpand(filme.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemFilme(filme));
    }

    @GetMapping
    public List<Filme> listar(@PageableDefault(size = 10, sort = "titulo") Pageable paginacao){
        return filmeRepository.findAll(paginacao).toList();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaFilme dados) {
        var filme = filmeRepository.getReferenceById(dados.id());
        filme.atualizaDados(dados);

        return ResponseEntity.ok(new DadosListagemFilme(filme));
    }
}
