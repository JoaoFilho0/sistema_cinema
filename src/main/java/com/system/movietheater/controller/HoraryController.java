package com.system.movietheater.controller;

import com.system.movietheater.domain.movietheaterhorary.MovieTheaterHorary;
import com.system.movietheater.domain.movietheaterhorary.MovieTheaterHoraryRepository;
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
    private MovieTheaterHoraryRepository movieTheaterHoraryRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DataRegisterMovieTheaterHorary data) {
        var movieTheaterHorary = new MovieTheaterHorary(data);

        movieTheaterHoraryRepository.save(movieTheaterHorary);
    }
}
