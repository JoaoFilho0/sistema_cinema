package com.sistema.cinema.domain.cinema;

import com.sistema.cinema.domain.endereco.Endereco;
import com.sistema.cinema.domain.horario.Horario;
import com.sistema.cinema.domain.sala.Salas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "cinema")
@Entity(name = "Cinema")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cin_id")
    private Long id;

    @Column(name = "cin_nome")
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_endereco_id")
    private Endereco endereco;

    @OneToMany
    @JoinTable(
            name = "cinema_salas",
            joinColumns = @JoinColumn(name = "fk_cinema_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_salas_id")
    )
    private List<Salas> salas;

    @OneToMany
    @JoinTable(
            name = "cinema_horario",
            joinColumns = @JoinColumn(name = "fk_cinema_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_horario_id")
    )
    private List<Horario> horarios;

    public Cinema(DadosCadastroCinema dados) {
        this.nome = dados.nome();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizaDados(DadosAtualizaCinema dados) {
        if (this.nome != null) {
            this.nome = dados.nome();
        }
        if (this.endereco != null) {
            this.endereco.atualizaInformacoes(dados.endereco());
        }
    }
}
