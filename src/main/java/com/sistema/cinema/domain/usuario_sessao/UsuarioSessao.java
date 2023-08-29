package com.sistema.cinema.domain.usuario_sessao;

import com.sistema.cinema.domain.sessao.DadosSessao;
import com.sistema.cinema.domain.sessao.Sessao;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "usuario_sessao")
@Entity(name = "UsuarioSessao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuarioSessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_ses_id")
    private Long id;

    @Column(name = "usu_ses_check")
    private int check;

    @Column(name = "fk_usuario_id")
    private Long usuario;

    @OneToOne
    @JoinColumn(name = "fk_sessao_id")
    private Sessao sessao;

    public UsuarioSessao(DadosCadastroUSuarioSessao dados) {
        this.check = dados.check();
        this.usuario = dados.usuario();
        this.sessao = new Sessao(dados.sessao());
    }
}
