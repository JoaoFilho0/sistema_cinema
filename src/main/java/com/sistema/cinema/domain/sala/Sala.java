package com.sistema.cinema.domain.sala;

import com.sistema.cinema.domain.cinema.Cinema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "sala_de_cinema")
@Entity(name = "Sala")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sala_id")
    private Long id;

    @Column(name = "sala_num")
    private int numero;

    @Column(name = "sala_bancos")
    private int assentos;

    @JoinColumn(name = "fk_cinema_id")
    @ManyToOne
    private Cinema cinema;

    public Sala(DadosCadastroSala dados) {
        this.numero = dados.numero();
        this.assentos = dados.assentos();
        this.cinema = dados.cinema();
    }

    public void atualizaDados(DadosAtualizaSala dados) {
        if (this.numero != 0) {
            this.numero = dados.numero();
        }
        if (this.assentos != 0) {
            this.assentos = dados.assentos();
        }
    }
}
