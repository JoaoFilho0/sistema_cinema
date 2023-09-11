package com.system.movietheater.domain.session;

import com.system.movietheater.domain.movie.Movie;
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
    @JoinColumn(name = "sessao_id", nullable = false)
    private Movie movie;

    public Session(DataSession data) {
        this.horary = data.horary();
        this.tickets = data.tickets();
        this.price = data.price();
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
