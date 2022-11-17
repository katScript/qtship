package com.spring.app.orders.models.repository;

import com.spring.app.customers.models.Customer;
import com.spring.app.orders.models.Order;
import com.spring.app.shipping.models.Shipper;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer (Customer customer);

    Page<Order> findAll(Pageable pageable);

    @Query("select o from Order o where o.customer = :customer and ( :status is null or o.status = :status ) and o.createdAt between :from and :to")
    Page<Order> findByCustomerAndStatusAndCreatedAtBetween (Customer customer, String status, Date from, Date to, Pageable pageable);

    @Query("select o from Order o where o.shipper = :shipper and ( :status is null or o.status = :status ) and o.createdAt between :from and :to")
    Page<Order> findByShipperAndStatusAndCreatedAtBetween (Shipper shipper, String status, Date from, Date to, Pageable pageable);

    @Query("select o from Order o where ( :status is null or o.status = :status ) and o.createdAt between :from and :to")
    Page<Order> findAllByStatusAndCreatedAtBetween (String status, Date from, Date to, Pageable pageable);


}
