package com.sistema.cinema.domain.sala;

import com.sistema.cinema.domain.cinema.Cinema;
import com.sistema.cinema.domain.cinema_salas.CinemaSalas;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "salas")
@Entity(name = "Salas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Salas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sala_id")
    private Long id;

    @Column(name = "sala_numero")
    private int numero;

    @Column(name = "sala_assentos")
    private int assentos;

    public Salas(DadosCadastroSalas dados) {
        this.numero = dados.numero();
        this.assentos = dados.assentos();
    }

    public void atualizaDados(DadosAtualizaSalas dados) {
        if (this.numero != 0) {
            this.numero = dados.numero();
        }
        if (this.assentos != 0) {
            this.assentos = dados.assentos();
        }
    }
}
