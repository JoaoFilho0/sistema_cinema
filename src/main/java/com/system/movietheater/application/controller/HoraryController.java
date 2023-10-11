package com.system.movietheater.application.controller;

import com.system.movietheater.application.dto.horary.DataRegisterHorary;
import com.system.movietheater.application.dto.horary.DataUpdateHorary;
import com.system.movietheater.domain.model.Horary;
import com.system.movietheater.usercase.horary.HoraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(summary = "Save horary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful request: Horary saved.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Day, horary or/and movie theater is null." +
                    "<br>Horary already exists in movie theater." +
                    "<br>Format horary invalid.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie theater not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Horary> register(@RequestBody @Valid DataRegisterHorary data) {
        return ResponseEntity.ok(horaryService.registerHorary(data));
    }

    @GetMapping("/{id}")
    @Operation(summary = "List horary by movie theater id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: Horary listed.", content = @Content)
    })
    public ResponseEntity<List<Horary>> list(@PathVariable Long id) {
        return ResponseEntity.ok(horaryService.list(id));
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Update horary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful request: Horary updated.", content = @Content),
            @ApiResponse(responseCode = "400", description = "Horary id or/and movie theater is null.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Horary not found." +
                    "<br>Movie theater not found.", content = @Content),
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Horary> update(@RequestBody @Valid DataUpdateHorary data) {
        return ResponseEntity.ok(horaryService.updateHorary(data));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete horary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful request: Horary deleted.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Horary not found.")
    })
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        horaryService.deleteHorary(id);

        return ResponseEntity.noContent().build();
    }
}
