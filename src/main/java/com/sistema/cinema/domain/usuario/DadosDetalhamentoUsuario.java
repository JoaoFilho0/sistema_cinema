package com.sistema.cinema.domain.usuario;

import com.sistema.cinema.domain.cinema.Cinema;

public record DadosDetalhamentoUsuario(Long id, String nome, String email, Cinema cinema) {

    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCinema());
    }

}
