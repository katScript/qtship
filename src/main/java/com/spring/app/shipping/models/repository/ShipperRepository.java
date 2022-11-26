package com.spring.app.shipping.models.repository;

import com.spring.app.authentication.models.User;
import com.spring.app.shipping.models.Shipper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Long> {
    Optional<Shipper> findByUser(User user);

    Page<Shipper> findAll(Pageable pageable);

    @Query("select s from Shipper s where (:id is null or s.id = :id) " +
            "and (:fullName is null or s.fullName = :fullName) " +
            "and (:phone is null or s.phone = :phone) " +
            "and (:email is null or s.email = :email)")
    Page<Shipper> findAllWithFilter(Long id, String fullName, String phone, String email, Pageable pageable);
}

