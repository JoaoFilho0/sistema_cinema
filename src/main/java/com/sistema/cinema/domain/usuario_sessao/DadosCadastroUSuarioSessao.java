package com.sistema.cinema.domain.usuario_sessao;

import com.sistema.cinema.domain.sessao.DadosSessao;
import com.sistema.cinema.domain.sessao.Sessao;

public record DadosCadastroUSuarioSessao(
        int check,
        Long usuario,
        DadosSessao sessao
) {
}
