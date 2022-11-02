package com.spring.app.shipping.models.repository;

import com.spring.app.authentication.models.User;
import com.spring.app.shipping.models.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Long> {
    Optional<Shipper> findByUser(User user);

}

