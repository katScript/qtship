package com.spring.app.warehouse.models.repository;

import com.spring.app.customers.models.Customer;
import com.spring.app.warehouse.models.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Page<Warehouse> findAll(Pageable pageable);

    Page<Warehouse> findByCustomer(Customer customer, Pageable pageable);
}
