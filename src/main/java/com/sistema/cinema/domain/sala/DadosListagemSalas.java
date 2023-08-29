package com.sistema.cinema.domain.sala;

import com.sistema.cinema.domain.cinema.Cinema;

public record DadosListagemSalas(Long id, int numero, int assentos) {

    public DadosListagemSalas(Salas salas) {
        this(salas.getId(), salas.getNumero(), salas.getAssentos());
    }

}
