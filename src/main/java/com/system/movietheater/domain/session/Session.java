package com.system.movietheater.domain.session;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "sessao")
@Entity(name = "Sessao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Session {

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


    public Session(DataSession dados) {
        this.horario = dados.horario();
        this.ingressos = dados.ingressos();
        this.preco = dados.preco();
    }

    public void atualizaDados(DataUpdateSession dados) {
        if (dados.ingressos() > 0) {
            this.ingressos = dados.ingressos();
        }
        if (dados.preco() != 0) {
            this.preco = dados.preco();
        }
    }
}
