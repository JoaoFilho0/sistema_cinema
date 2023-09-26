package com.system.movietheater.domain.address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT end_id, end_numero, end_rua, end_bairro, end_cidade " +
            "FROM endereco WHERE end_numero=:number AND end_rua=:street AND end_bairro=:district " +
            "AND end_cidade=:city", nativeQuery = true)
    Address findByNumberAndStreetAndDistrictAndCity(String number, String street, String district, String city);
}
