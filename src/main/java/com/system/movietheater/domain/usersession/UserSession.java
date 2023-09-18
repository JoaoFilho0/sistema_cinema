package com.system.movietheater.domain.usersession;

import com.system.movietheater.domain.session.Session;
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

    @Column(name = "fk_usuario_id")
    private Long user;

    @OneToOne
    @JoinColumn(name = "fk_sessao_id")
    private Session session;

    public UserSession(DataRegisterUserSession data) {
        this.check = 1;
        this.user = data.user();
        this.session = new Session(data.session());
    }

    public void sessionCheck() {
        this.check = 2;
    }
}
