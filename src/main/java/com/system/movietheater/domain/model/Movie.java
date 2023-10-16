package com.system.movietheater.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.system.movietheater.application.dto.movie.RegisterMovieDto;
import com.system.movietheater.application.dto.movie.UpdateMovieDto;
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

    @JsonIgnore
    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER)
    private List<Session> session;

    public Movie(RegisterMovieDto data) {
        this.title = data.title();
        this.duration = data.duration();
    }

    public Movie(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.duration = movie.duration;
    }

    public void updateData(UpdateMovieDto data) {
        if(data.title() != null){
            this.title = data.title();
        }
        if(data.duration() != 0) {
            this.duration = data.duration();
        }
    }
}
