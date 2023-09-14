package com.system.movietheater.domain.movietheater;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.system.movietheater.domain.address.Address;
import com.system.movietheater.domain.horary.Horary;
import com.system.movietheater.domain.room.Room;
import com.system.movietheater.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "cinema")
@Entity(name = "Cinema")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class    MovieTheater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cin_id")
    private Long id;

    @Column(name = "cin_nome")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_endereco_id")
    private Address address;


    @OneToMany(mappedBy = "movieTheater")
    private List<Room> rooms;

    @OneToMany(mappedBy = "movieTheater", fetch = FetchType.EAGER)
    private List<Horary> horaries;

    public MovieTheater(DataRegisterMovieTheater data) {
        this.name = data.name();
        this.address = new Address(data.address());
    }

    public MovieTheater(User user) {
        this.id = user.getMovieTheater().getId();
        this.name = user.getMovieTheater().getName();
        this.address = user.getMovieTheater().getAddress();
        this.rooms = user.getMovieTheater().getRooms();
        this.horaries = user.getMovieTheater().getHoraries();
    }

    public void updateData(DataUpdateMovieTheater data) {
        if (this.name != null) {
            this.name = data.name();
        }
        if (this.address != null) {
            this.address.updateData(data.address());
        }
    }
}
