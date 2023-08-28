package com.sistema.cinema.controller;

import com.sistema.cinema.domain.sessao.DadosCadastroSessao;
import com.sistema.cinema.domain.sessao.DadosListagemSessao;
import com.sistema.cinema.domain.sessao.Sessao;
import com.sistema.cinema.domain.sessao.SessaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cinema/sessao")
public class SessaoController {

    @Autowired
    private SessaoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroSessao dados, UriComponentsBuilder uriBuilder) {
        var sessao = new Sessao(dados);
        repository.save(sessao);

        var uri = uriBuilder.path("cinema/sessao/{id}").buildAndExpand(sessao.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemSessao(sessao));
    }

}
