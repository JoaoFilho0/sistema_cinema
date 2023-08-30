package com.system.movietheater.domain.sessionroom;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "sessao_salas")
@Entity(name = "SessaoSalas")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ses_salas_id")
    private Long id;

    @Column(name = "fk_sessao_id")
    private Long sessao;

    @Column(name = "fk_salas_id")
    private Long sala;

    public SessionRoom(DataRegisterSessionRoom dados) {
        this.sessao = dados.sessao();;
        this.sala = dados.sala();
    }
}
