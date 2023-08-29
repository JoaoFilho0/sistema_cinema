package com.sistema.cinema.controller;

import com.sistema.cinema.domain.cinema.*;
import com.sistema.cinema.domain.usuario.UsuarioRepository;
import com.sistema.cinema.domain.endereco.EnderecoRepository;
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
public class CinemaController {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCinema dados, UriComponentsBuilder uriBuilder) {
        var cinema = new Cinema(dados);
        var cliente = usuarioRepository.getReferenceById(dados.cliente());

        var cinemaDados = cinemaRepository.save(cinema);
        cliente.setCinema(cinemaDados);

        usuarioRepository.save(cliente);

        var uri = uriBuilder.path("cinema/{id}").buildAndExpand(cinema.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCinema(cinema));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemCinema>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = cinemaRepository.findAll(paginacao).map(DadosListagemCinema::new).toList();
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizaCinema dados) {
        var cinema = cinemaRepository.getReferenceById(dados.id());
        cinema.atualizaDados(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCinema(cinema));
    }
}
