package com.system.movietheater.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.system.movietheater.application.dto.horary.RegisterHoraryDto;
import com.system.movietheater.application.dto.horary.UpdateHoraryDto;
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_cinema_id", nullable = false)
    private MovieTheater movieTheater;

    public Horary(RegisterHoraryDto data) {
        this.day = data.day();
        this.horary = data.horary();
        this.movieTheater = new MovieTheater(data.movieTheater());
    }

    public void updateData(UpdateHoraryDto data) {
        if(data.day() != null) {
            this.day = data.day();
        }
        if (data.horary() != null) {
            this.horary = data.horary();
        }
    }
}
