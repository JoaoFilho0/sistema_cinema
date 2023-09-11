package com.system.movietheater.controller;

import com.system.movietheater.domain.horary.DataRegisterHorary;
import com.system.movietheater.domain.horary.Horary;
import com.system.movietheater.domain.horary.HoraryRepository;
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
    private HoraryRepository horaryRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DataRegisterHorary data) {
        var horary = new Horary(data);

        horaryRepository.save(horary);
    }
}
