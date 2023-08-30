package com.system.movietheater.domain.sessionmovie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "sessao_filmes")
@Entity(name = "SessaoFilme")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ses_fil_id")
    private Long id;

    @Column(name = "fk_sessao_id")
    private Long sessao;

    @Column(name = "fk_filmes_id")
    private Long filme;

    public SessionMovie(DataRegisterSessionMovie dados) {
        this.sessao = dados.sessao();;
        this.filme = dados.filme();
    }
}
