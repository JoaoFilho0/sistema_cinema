package com.sistema.cinema.domain.filme;

import com.sistema.cinema.domain.cinema.DadosAtualizaCinema;
import com.sistema.cinema.domain.sala.Salas;
import com.sistema.cinema.domain.sessao.Sessao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "filmes")
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

    @OneToMany
    @JoinTable(
            name = "sessao_filmes",
            joinColumns = @JoinColumn(name = "fk_sessao_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_filmes_id")
    )
    private List<Sessao> sessao;

    public Filme(DadosCadastroFilme dados) {
        this.titulo = dados.titulo();
        this.duracao = dados.duracao();
    }

    public void atualizaDados(DadosAtualizaFilme dados) {
        if(dados.titulo() != null){
            this.titulo = dados.titulo();
        }
        if(dados.duracao() != 0) {
            this.duracao = dados.duracao();
        }
    }
}
