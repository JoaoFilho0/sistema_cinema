package com.sistema.cinema.controller;

import com.sistema.cinema.domain.cinema_horario.CinemaHorario;
import com.sistema.cinema.domain.cinema_horario.CinemaHorarioRepository;
import com.sistema.cinema.domain.cinema_horario.DadosCadastroCinemaHorario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cinema/horario")
public class HorarioController {

    @Autowired
    private CinemaHorarioRepository cinemaHorarioRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroCinemaHorario dados) {
        var cinemaHorario = new CinemaHorario(dados);

        cinemaHorarioRepository.save(cinemaHorario);
    }
}
