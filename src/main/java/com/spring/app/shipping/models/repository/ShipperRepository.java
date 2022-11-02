package com.spring.app.shipping.models.repository;

import com.spring.app.shipping.models.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Long> {

}

