package com.system.movietheater.domain.room;

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

    public Room(DataRegisterRoom data) {
        this.number = data.number();
        this.seats = data.seats();
    }

    public void updateData(DataUpdateRoom data) {
        if (data.number() != 0) {
            this.number = data.number();
        }
        if (data.seats() != 0) {
            this.seats = data.seats();
        }
    }
}
