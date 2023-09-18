package com.system.movietheater.domain.session;

import com.system.movietheater.domain.movie.Movie;
import com.system.movietheater.domain.room.Room;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "sessao")
@Entity(name = "Sessao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ses_id")
    private Long id;

    @Column(name = "ses_horario")
    private String horary;

    @Column(name = "ses_quantidade_ingresso")
    private int tickets;

    @Column(name = "ses_preco_ingresso")
    private float price;

    @ManyToOne
    @JoinColumn(name = "fk_filme_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "fk_sala_id")
    private Room room;

    public Session(DataRegisterSession data) {
        this.horary = data.horary();
        this.tickets = data.tickets();
        this.price = data.price();
        this.movie = data.movie();
        this.room = data.room();
    }

    public Session(Session session) {
        this.id = session.getId();
        this.horary = session.getHorary();
        this.tickets = session.getTickets();
        this.price = session.getPrice();
        this.movie = session.getMovie();
        this.room = session.getRoom();
    }

    public Session(DataListingSession session) {
        this.id = session.id();
        this.horary = session.horary();
        this.tickets = session.tickets();
        this.price = session.price();
        this.movie = session.movie();
        this.room = session.room();
    }

    public void updateData(DataUpdateSession data) {
        if (data.tickets() > 0) {
            this.tickets = data.tickets();
        }
        if (data.price() != 0) {
            this.price = data.price();
        }
    }
}
