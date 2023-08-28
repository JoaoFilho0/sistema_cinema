package com.sistema.cinema.domain.sala;

import com.sistema.cinema.domain.cinema.Cinema;
import com.sistema.cinema.domain.cinema.CinemaRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Table(name = "sala_de_cinema")
@Entity(name = "Sala")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sala_id")
    private Long id;

    @Column(name = "sala_num")
    private int numero;

    @Column(name = "sala_bancos")
    private int assentos;

    @ManyToOne
    @JoinColumn(name = "fk_cinema_id")
    private Cinema cinema;

    public Sala(DadosCadastroSala dados) {
        this.numero = dados.numero();
        this.assentos = dados.assentos();
    }

    public void atualizaDados(DadosAtualizaSala dados) {
        if (this.numero != 0) {
            this.numero = dados.numero();
        }
        if (this.assentos != 0) {
            this.assentos = dados.assentos();
        }
    }
}
