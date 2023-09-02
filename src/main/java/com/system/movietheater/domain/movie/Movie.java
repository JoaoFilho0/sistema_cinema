package com.system.movietheater.domain.movie;

import com.system.movietheater.domain.session.Session;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "filmes")
@Entity(name = "Filme")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fil_id")
    private Long id;

    @Column(name = "fil_nome")
    private String title;

    @Column(name = "fil_duracao")
    private int duration;

    @OneToMany
    @JoinTable(
            name = "sessao_filmes",
            joinColumns = @JoinColumn(name = "fk_sessao_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_filmes_id")
    )
    private List<Session> session;

    public Movie(DataRegisterMovie data) {
        this.title = data.title();
        this.duration = data.duration();
    }

    public void updateData(DataUpdateMovie data) {
        if(data.title() != null){
            this.title = data.title();
        }
        if(data.duration() != 0) {
            this.duration = data.duration();
        }
    }
}
