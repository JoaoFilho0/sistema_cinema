package com.sistema.cinema.controller;

import com.sistema.cinema.domain.cinema.Cinema;
import com.sistema.cinema.domain.cinema.CinemaRepository;
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

import java.util.List;

@RestController
@RequestMapping("cinema/sala")
public class SalaController {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroSala dados) {
        var sala = new Sala(dados);
        var cinema = cinemaRepository.getReferenceById(dados.cinema());

        sala.setCinema(cinema);

        salaRepository.save(sala);
    }

    @GetMapping("/{cinema_id}")
    public List<Sala> listar(@PathVariable Long cinema_id, @PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
//        var page = salaRepository.findAll(paginacao).map(DadosListagemSala::new);
        var cinema = cinemaRepository.getReferenceById(cinema_id);
        var page = cinema.getSalas();

        return page;
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizaSala dados) {
        var sala = salaRepository.getReferenceById(dados.id());
        sala.atualizaDados(dados);
    }

}
