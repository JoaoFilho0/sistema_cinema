package com.system.movietheater.domain.movietheaterroom;

import com.system.movietheater.domain.room.Room;
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
public class MovieTheaterRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cin_sala_id")
    private Long id;

    @Column(name = "fk_cinema_id")
    private Long movieTheater;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_salas_id")
    private Room room;

    public MovieTheaterRoom(DataRegisterMovieTheaterRoom data) {
        this.movieTheater = data.movieTheater();
        this.room = new Room(data.room());
    }
}
