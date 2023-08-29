package com.sistema.cinema.controller;

import com.sistema.cinema.domain.cinema.CinemaRepository;
import com.sistema.cinema.domain.cinema_salas.CinemaSalas;
import com.sistema.cinema.domain.cinema_salas.CinemaSalasRepository;
import com.sistema.cinema.domain.cinema_salas.DadosCadastroCinemaSalas;
import com.sistema.cinema.domain.sala.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cinema/sala")
public class SalaController {

    @Autowired
    private SalasRepository salasRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private CinemaSalasRepository cinemaSalasRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroCinemaSalas dados) {
        var cinemaSalas = new CinemaSalas(dados);

        cinemaSalasRepository.save(cinemaSalas);
    }

    @GetMapping("/{cinema_id}")
    public List<Salas> listar(@PathVariable Long cinema_id, @PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        var cinema = cinemaRepository.getReferenceById(cinema_id);
        var page = cinema.getSalas();

        return page;
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizaSalas dados) {
        var sala = salasRepository.getReferenceById(dados.id());
        sala.atualizaDados(dados);
    }

}
