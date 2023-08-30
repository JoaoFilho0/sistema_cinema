package com.system.movietheater.controller;

import com.system.movietheater.domain.movietheaterhorary.MovieTheaterHorary;
import com.system.movietheater.domain.movietheaterhorary.MovieTheaterRepository;
import com.system.movietheater.domain.movietheaterhorary.DataRegisterMovieTheaterHorary;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cinema/horario")
public class HoraryController {

    @Autowired
    private MovieTheaterRepository movieTheaterRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DataRegisterMovieTheaterHorary dados) {
        var cinemaHorario = new MovieTheaterHorary(dados);

        movieTheaterRepository.save(cinemaHorario);
    }
}
