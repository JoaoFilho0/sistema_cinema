package com.sistema.cinema.domain.usuario;

import com.sistema.cinema.domain.cinema.Cinema;
import com.sistema.cinema.domain.sala.Salas;
import com.sistema.cinema.domain.sessao.Sessao;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private Long id;

    @Column(name = "usu_nome")
    private String nome;

    @Column(name = "usu_email")
    private String email;

    @Column(name = "usu_senha")
    private String senha;

    @OneToOne
    @JoinColumn(name = "fk_cinema_id")
    private Cinema cinema;

    @OneToMany
    @JoinTable(
            name = "usuario_sessao",
            joinColumns = @JoinColumn(name = "fk_usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "fk_sessao_id")
    )
    private List<Sessao> sessao;

    public Usuario(DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
    }

    public void atualizaDados(DadosAtualizaUsuario dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
    }
}
