package com.spring.app.orders.models.repository;

import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderLogRepository extends JpaRepository<OrderLog, Long> {
    Optional<OrderLog> findByOrder(Order order);
}
