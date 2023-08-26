package com.sistema.cinema.domain.cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cliente")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cli_id;
    private String cli_nome;
    private String cli_email;
    private String cli_senha;

    public Cliente(DadosCadastroCliente dados) {
        this.cli_nome = dados.cli_nome();
        this.cli_email = dados.cli_email();
        this.cli_senha = dados.cli_senha();
    }

    public void atualizaDados(DadosAtualizaCliente dados) {
        if (dados.nome() != null) {
            this.cli_nome = dados.nome();
        }
        if (dados.email() != null) {
            this.cli_email = dados.email();
        }
    }
}
