package com.system.movietheater.domain.address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Override
    Page<Address> findAll(Pageable paginacao);
}
