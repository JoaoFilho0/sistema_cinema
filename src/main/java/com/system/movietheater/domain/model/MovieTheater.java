package com.system.movietheater.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.system.movietheater.application.dto.movietheater.DataListingMovieTheater;
import com.system.movietheater.application.dto.movietheater.DataRegisterMovieTheater;
import com.system.movietheater.application.dto.movietheater.DataUpdateMovieTheater;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "cinema")
@Entity(name = "Cinema")
@Getter
@Setter
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_usuario_id")
    private User user;

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

    public MovieTheater(MovieTheater movieTheater) {
        this.id = movieTheater.getId();
        this.name = movieTheater.getName();
        this.address = movieTheater.getAddress();
        this.rooms = movieTheater.getRooms();
        this.horaries = movieTheater.getHoraries();
    }

    public MovieTheater(DataListingMovieTheater dataListingMovieTheater) {
        this.id = dataListingMovieTheater.id();
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
