package com.sistema.cinema.domain.cinema_horario;

import com.sistema.cinema.domain.horario.Horario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cinema_horario")
@Entity(name = "CinemaHorario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CinemaHorario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cin_hor_id")
    private Long id;

    @Column(name = "fk_cinema_id")
    private Long cinema;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_horario_id")
    private Horario horario;

    public CinemaHorario(DadosCadastroCinemaHorario dados) {
        this.cinema = dados.cinema();
        this.horario = new Horario(dados.horario());
    }
}
