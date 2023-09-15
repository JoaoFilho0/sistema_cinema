package com.system.movietheater.controller;

import com.system.movietheater.domain.horary.DataRegisterHorary;
import com.system.movietheater.domain.horary.Horary;
import com.system.movietheater.domain.horary.HoraryRepository;
import com.system.movietheater.domain.horary.HoraryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cinema/horario")
public class HoraryController {

    @Autowired
    private HoraryService horaryService;

    @PostMapping
    @Transactional
    public ResponseEntity<Horary> register(@RequestBody @Valid DataRegisterHorary data) {
        return ResponseEntity.ok(horaryService.registerHorary(data));
    }
}
