package com.system.movietheater.controller;

import com.system.movietheater.domain.horary.DataRegisterHorary;
import com.system.movietheater.domain.horary.DataUpdateHorary;
import com.system.movietheater.domain.horary.Horary;
import com.system.movietheater.domain.horary.HoraryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<List<Horary>> list(@PathVariable Long id) {
        return ResponseEntity.ok(horaryService.list(id));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Horary> update(@RequestBody @Valid DataUpdateHorary data) {
        return ResponseEntity.ok(horaryService.updateHorary(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        horaryService.deleteHorary(id);

        return ResponseEntity.noContent().build();
    }
}
