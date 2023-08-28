package com.sistema.cinema.domain.filme;

import com.sistema.cinema.domain.sessao.Sessao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "filme")
@Entity(name = "Filme")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fil_id")
    private Long id;

    @Column(name = "fil_nome")
    private String titulo;

    @Column(name = "fil_duracao")
    private int duracao;

    @OneToMany(mappedBy = "filme")
    private List<Sessao> sessoes;

    public Filme(DadosCadastroFilme dados) {

    }
}
