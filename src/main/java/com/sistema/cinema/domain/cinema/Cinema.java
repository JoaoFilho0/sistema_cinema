package com.sistema.cinema.domain.cinema;

import com.sistema.cinema.domain.cliente.Cliente;
import com.sistema.cinema.domain.endereco.DadosAtualizaEndereco;
import com.sistema.cinema.domain.endereco.DadosCadastroEndereco;
import com.sistema.cinema.domain.endereco.Endereco;
import com.sistema.cinema.domain.sala.Sala;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "cinema")
@Entity(name = "Cinema")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cin_id")
    private Long id;

    @Column(name = "cin_nome")
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_endereco_id")
    private Endereco endereco;

    @OneToMany
    @JoinColumn(name = "fk_cinema_id")
    private List<Sala> salas;

    public Cinema(DadosCadastroCinema dados) {
        this.nome = dados.nome();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizaDados(DadosAtualizaCinema dados) {
        if (this.nome != null) {
            this.nome = dados.nome();
        }
        if (this.endereco != null) {
            this.endereco.atualizaInformacoes(dados.endereco());
        }
    }
}
