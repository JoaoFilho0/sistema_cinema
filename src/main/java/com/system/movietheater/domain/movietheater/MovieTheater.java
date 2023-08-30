package com.system.movietheater.domain.movietheater;

import com.system.movietheater.domain.address.Address;
import com.system.movietheater.domain.horary.Horary;
import com.system.movietheater.domain.room.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "cinema")
@Entity(name = "Cinema")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MovieTheater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cin_id")
    private Long id;

    @Column(name = "cin_nome")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_endereco_id")
    private Address address;

    @OneToMany
    @JoinTable(
            name = "cinema_salas",
            joinColumns = @JoinColumn(name = "fk_cinema_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_salas_id")
    )
    private List<Room> room;

    @OneToMany
    @JoinTable(
            name = "cinema_horario",
            joinColumns = @JoinColumn(name = "fk_cinema_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_horario_id")
    )
    private List<Horary> horaries;

    public MovieTheater(DataRegisterMovieTheater data) {
        this.name = data.nome();
        this.address = new Address(data.endereco());
    }

    public void atualizaDados(DataUpdateMovieTheater dados) {
        if (this.name != null) {
            this.name = dados.nome();
        }
        if (this.address != null) {
            this.address.atualizaInformacoes(dados.endereco());
        }
    }
}
