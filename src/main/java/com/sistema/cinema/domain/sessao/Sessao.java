package com.sistema.cinema.domain.sessao;

import com.sistema.cinema.domain.filme.Filme;
import com.sistema.cinema.domain.sala.Salas;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "sessao")
@Entity(name = "Sessao")
@Getter
@Builder
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

    @Column(name = "ses_quant_ingresso")
    private int ingressos;

    @Column(name = "ses_ingresso_preco")
    private float preco;


    public Sessao(DadosCadastroSessao dados) {
        this.horario = dados.horario();
        this.ingressos = dados.ingressos();
        this.preco = dados.preco();
    }

    private void createSessao(DadosCadastroSessao dados) {

    }
}
