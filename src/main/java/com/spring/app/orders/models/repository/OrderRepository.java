package com.spring.app.orders.models.repository;

import com.spring.app.customers.models.Customer;
import com.spring.app.orders.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer (Customer customer);
}
