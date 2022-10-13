package com.spring.app.customers.repository;

import com.spring.app.customers.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
