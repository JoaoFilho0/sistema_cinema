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
    private String city;

    @Column(name = "end_bairro")
    private String district;

    @Column(name = "end_rua")
    private String street;

    @Column(name = "end_numero", nullable = false)
    private String number;

    public Address(DataRegisterAddress data){
        this.city = data.city();
        this.district = data.district();
        this.street = data.street();
        this.number = data.number();
    }

    public void updateData(DataUpdateAddress data) {
        if (data.city() != null) {
            this.city = data.city();
        }
        if (data.district() != null) {
            this.district = data.district();
        }
        if (data.street() != null) {
            this.street = data.street();
        }
        if (data.number() != null) {
            this.number = data.number();
        }
    }
}
