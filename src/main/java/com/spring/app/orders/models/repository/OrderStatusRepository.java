package com.spring.app.orders.models.repository;

import com.spring.app.orders.models.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus,Long> {
    Optional<OrderStatus> findByCode(String code);
}
