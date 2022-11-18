package com.spring.app.price.models.repository;

import com.spring.app.price.models.Coupon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByCode(String code);
    Page<Coupon> findAll(Pageable pageable);
    @Query("select c from Coupon c where c.enable = true and c.validFrom <= current_date and c.validTo >= current_date")
    List<Coupon> findCouponByEnable();
}
