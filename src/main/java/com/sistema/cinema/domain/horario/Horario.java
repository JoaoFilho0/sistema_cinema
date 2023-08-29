package com.sistema.cinema.domain.horario;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "horario")
@Entity(name = "Horario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hor_id")
    private Long id;

    @Column(name = "hor_dia")
    private String dia;

    @Column(name = "hor_horario")
    private String horario;

    public Horario(DadosCadastroHorario dados) {
        this.dia = dados.dia();
        this.horario = dados.horario();
    }
}
