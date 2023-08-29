package com.sistema.cinema.domain.filme;

public record DadosListagemFilme(Long id, String titulo, int duracao) {
    public DadosListagemFilme(Filme filme) {
        this(filme.getId(), filme.getTitulo(), filme.getDuracao());
    }
}
