package com.spring.app.orders.models.repository;

import com.spring.app.customers.models.Customer;
import com.spring.app.orders.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer (Customer customer);

    @Query("select o from Order o where o.customer = ?1 and o.status = ?2 and o.createdAt between ?3 and ?4 order by o.createdAt desc")
    List<Order> findByCustomerAndStatusAndCreatedAtBetween (Customer customer, String status, Date from, Date to);
}
