package com.sistema.cinema.domain.cinema_salas;

import com.sistema.cinema.domain.sala.Salas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cinema_salas")
@Entity(name = "CinemaSalas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CinemaSalas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cin_sala_id")
    private Long id;

    @Column(name = "fk_cinema_id")
    private Long cinema;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_salas_id")
    private Salas sala;

    public CinemaSalas(DadosCadastroCinemaSalas dados) {
        this.cinema = dados.cinema();
        this.sala = new Salas(dados.sala());
    }
}
