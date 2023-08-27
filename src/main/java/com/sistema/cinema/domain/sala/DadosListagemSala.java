package com.sistema.cinema.domain.sala;

import com.sistema.cinema.domain.cinema.Cinema;

public record DadosListagemSala(Long id, int numero, int assentos, Cinema cinema) {

    public DadosListagemSala(Sala sala) {
        this(sala.getId(), sala.getNumero(), sala.getAssentos(), sala.getCinema());
    }

}
