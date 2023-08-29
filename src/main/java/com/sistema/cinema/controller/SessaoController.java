package com.sistema.cinema.controller;

import com.sistema.cinema.domain.sessao.DadosCadastroSessao;
import com.sistema.cinema.domain.sessao.DadosListagemSessao;
import com.sistema.cinema.domain.sessao.Sessao;
import com.sistema.cinema.domain.sessao.SessaoRepository;
import com.sistema.cinema.domain.sessao_filme.DadosCadastroSessaoFilme;
import com.sistema.cinema.domain.sessao_filme.SessaoFilme;
import com.sistema.cinema.domain.sessao_filme.SessaoFilmeRepository;
import com.sistema.cinema.domain.sessao_salas.DadosCadastroSessaoSalas;
import com.sistema.cinema.domain.sessao_salas.SessaoSalas;
import com.sistema.cinema.domain.sessao_salas.SessaoSalasRepository;
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
    private SessaoRepository sessaoRepository;

    @Autowired
    private SessaoFilmeRepository sessaoFilmeRepository;

    @Autowired
    private SessaoSalasRepository sessaoSalasRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroSessao dados, UriComponentsBuilder uriBuilder) {
        var sessao = new Sessao(dados.sessao());

        var dadosSessao = sessaoRepository.save(sessao);

        sessaoFilmeRepository.save(new SessaoFilme(new DadosCadastroSessaoFilme(dadosSessao.getId(), dados.filme())));
        sessaoSalasRepository.save(new SessaoSalas(new DadosCadastroSessaoSalas(dadosSessao.getId(), dados.sala())));


        var uri = uriBuilder.path("cinema/sessao/{id}").buildAndExpand(sessao.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosListagemSessao(sessao));
    }

}
