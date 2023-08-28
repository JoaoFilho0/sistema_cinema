package com.sistema.cinema.domain.sessao;

import com.sistema.cinema.domain.filme.Filme;
import com.sistema.cinema.domain.sala.DadosCadastroSala;
import com.sistema.cinema.domain.sala.Sala;
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

    @ManyToOne
    @JoinColumn(name = "fk_cinema_id")
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "fk_filme_id")
    private Filme filme;


    public Sessao(DadosCadastroSessao dados) {
        this.horario = dados.horario();
        this.ingressos = dados.ingressos();
        this.preco = dados.preco();
    }

    private void createSessao(DadosCadastroSessao dados) {

    }
}
