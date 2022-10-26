package com.spring.app.warehouse.models.repository;

import com.spring.app.warehouse.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
