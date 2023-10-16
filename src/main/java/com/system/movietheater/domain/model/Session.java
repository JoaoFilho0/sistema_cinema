package com.system.movietheater.domain.model;

import com.system.movietheater.application.dto.session.ListingSessionDto;
import com.system.movietheater.application.dto.session.RegisterSessionDto;
import com.system.movietheater.application.dto.session.UpdateSessionDto;
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

    @Column(name = "ses_data")
    private String date;

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

    public Session(RegisterSessionDto data) {
        this.date = data.date();
        this.horary = data.horary();
        this.price = data.price();
        this.movie = data.movie();
        this.room = data.room();
    }

    public Session(Session session) {
        this.id = session.getId();
        this.date = session.getDate();
        this.horary = session.getHorary();
        this.tickets = session.getTickets();
        this.price = session.getPrice();
        this.movie = session.getMovie();
        this.room = session.getRoom();
    }

    public Session(ListingSessionDto session) {
        this.id = session.id();
        this.date = session.date();
        this.horary = session.horary();
        this.tickets = session.tickets();
        this.price = session.price();
        this.movie = session.movie();
        this.room = session.room();
    }

    public void updateData(UpdateSessionDto data) {
        if (data.price() != 0) {
            this.price = data.price();
        }
    }
}
