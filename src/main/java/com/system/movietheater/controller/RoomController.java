package com.system.movietheater.controller;

import com.system.movietheater.domain.movietheaterroom.MovieTheaterRoom;
import com.system.movietheater.domain.movietheaterroom.DataRegisterMovieTheaterRoom;
import com.sistema.cinema.domain.room.*;
import com.system.movietheater.domain.room.DataUpdateRoom;
import com.system.movietheater.domain.room.Room;
import com.system.movietheater.domain.room.RoomRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cinema/sala")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private com.system.movietheater.domain.movietheater.MovieTheaterRepository movieTheaterRepository;

    @Autowired
    private com.system.movietheater.domain.movietheaterroom.MovieTheaterRepository movieTheaterRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DataRegisterMovieTheaterRoom dados) {
        var cinemaSalas = new MovieTheaterRoom(dados);

        movieTheaterRepository.save(cinemaSalas);
    }

    @GetMapping("/{cinema_id}")
    public List<Room> listar(@PathVariable Long cinema_id, @PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        //todo verificar se o cinema existe
        var cinema = movieTheaterRepository.getReferenceById(cinema_id);
        var page = cinema.getSalas();

        return page;
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DataUpdateRoom dados) {
        //todo verificar se o cinema existe
        var sala = roomRepository.getReferenceById(dados.id());
        sala.atualizaDados(dados);
    }

}
