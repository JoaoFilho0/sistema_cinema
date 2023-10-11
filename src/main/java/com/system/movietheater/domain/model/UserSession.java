package com.system.movietheater.domain.model;

import com.system.movietheater.application.dto.usersession.DataRegisterUserSession;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "usuario_sessao")
@Entity(name = "UsuarioSessao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_ses_id")
    private Long id;

    @Column(name = "usu_ses_check")
    private int check;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_sessao_id")
    private Session session;

    public UserSession(DataRegisterUserSession data) {
        this.check = 1;
        this.user = new User(data.user());
        this.session = data.session();
    }

    public void sessionCheck() {
        this.check = 2;
    }
}
