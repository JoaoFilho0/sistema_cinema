package com.system.movietheater.domain.horary;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "horario")
@Entity(name = "Horario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Horary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hor_id")
    private Long id;

    @Column(name = "hor_dia")
    private String dia;

    @Column(name = "hor_horario")
    private String horario;

    public Horary(DataRegisterHorary dados) {
        this.dia = dados.dia();
        this.horario = dados.horario();
    }
}
