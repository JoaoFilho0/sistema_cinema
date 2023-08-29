package com.sistema.cinema.domain.sessao;

import com.sistema.cinema.domain.filme.Filme;
import com.sistema.cinema.domain.sala.Salas;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "sessao")
@Entity(name = "Sessao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ses_id")
    private Long id;

    @Column(name = "ses_horario")
    private String horario;

    @Column(name = "ses_quantidade_ingresso")
    private int ingressos;

    @Column(name = "ses_preco_ingresso")
    private float preco;


    public Sessao(DadosSessao dados) {
        this.horario = dados.horario();
        this.ingressos = dados.ingressos();
        this.preco = dados.preco();
    }

    public void atualizaDados(DadosAtualizaSessao dados) {
        if (dados.ingressos() > 0) {
            this.ingressos = dados.ingressos();
        }
        if (dados.preco() != 0) {
            this.preco = dados.preco();
        }
    }
}
