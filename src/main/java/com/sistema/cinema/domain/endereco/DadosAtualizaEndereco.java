package com.sistema.cinema.domain.endereco;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizaEndereco(
        @NotBlank
        Long id,
        String cidade,
        String bairro,
        String rua,
        String numero
) {
}
