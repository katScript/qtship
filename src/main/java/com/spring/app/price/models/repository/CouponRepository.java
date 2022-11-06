package com.spring.app.price.models.repository;

import com.spring.app.price.models.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    public Optional<Coupon> findByCode(String code);
}
