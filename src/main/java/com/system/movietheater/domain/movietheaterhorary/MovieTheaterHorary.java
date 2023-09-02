package com.system.movietheater.domain.movietheaterhorary;

import com.system.movietheater.domain.horary.Horary;
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
public class MovieTheaterHorary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cin_hor_id")
    private Long id;

    @Column(name = "fk_cinema_id")
    private Long movieTheater;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_horario_id")
    private Horary horary;

    public MovieTheaterHorary(DataRegisterMovieTheaterHorary data) {
        this.movieTheater = data.movieTheater();
        this.horary = new Horary(data.horary());
    }
}
