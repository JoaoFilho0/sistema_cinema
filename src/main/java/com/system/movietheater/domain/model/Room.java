package com.system.movietheater.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.system.movietheater.application.dto.room.RegisterRoomDto;
import com.system.movietheater.application.dto.room.UpdateRoomDto;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "salas")
@Entity(name = "Salas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sala_id")
    private Long id;

    @Column(name = "sala_numero")
    private int number;

    @Column(name = "sala_assentos")
    private int seats;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cin_id")
    private MovieTheater movieTheater;

    public Room(RegisterRoomDto data) {
        this.number = data.number();
        this.seats = data.seats();
        this.movieTheater = new MovieTheater(data.movieTheater());
    }

    public Room(Room room) {
        this.id = room.getId();
        this.number = room.getNumber();
        this.seats = room.getSeats();
        this.movieTheater = room.getMovieTheater();
    }

    public void updateData(UpdateRoomDto data) {
        if (data.number() != 0) {
            this.number = data.number();
        }
        if (data.seats() != 0) {
            this.seats = data.seats();
        }
    }
}
