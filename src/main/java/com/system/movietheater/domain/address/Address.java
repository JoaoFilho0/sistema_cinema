package com.system.movietheater.domain.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "endereco")
@Entity(name = "Endereco")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "end_id")
    private Long id;

    @Column(name = "end_cidade")
    private String cidade;

    @Column(name = "end_bairro")
    private String bairro;

    @Column(name = "end_rua")
    private String rua;

    @Column(name = "end_numero")
    private String numero;

    public Address(DataRegisterAddress dados){
        this.cidade = dados.cidade();
        this.bairro = dados.bairro();
        this.rua = dados.rua();
        this.numero = dados.numero();
    }

    public void atualizaInformacoes(DataUpdateAddress dados) {
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if (dados.rua() != null) {
            this.rua = dados.rua();
        }
        if (dados.numero() != null) {
            this.numero = dados.numero();
        }
    }
}
