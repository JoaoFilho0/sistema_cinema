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
    private String day;

    @Column(name = "hor_horario")
    private String horary;

    public Horary(DataRegisterHorary data) {
        this.day = data.day();
        this.horary = data.horary();
    }
}
