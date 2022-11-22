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

    @Query("select o from Order o order by o.createdAt desc")
    Page<Order> findAll(Pageable pageable);

    @Query("select o from Order o where o.customer = :customer and ( :status is null or o.status = :status ) and o.createdAt between :from and :to order by o.createdAt desc")
    Page<Order> findByCustomerAndStatusAndCreatedAtBetween (Customer customer, String status, Date from, Date to, Pageable pageable);

    @Query("select o from Order o where o.shipper = :shipper and ( :status is null or o.status = :status ) and o.createdAt between :from and :to order by o.createdAt desc")
    Page<Order> findByShipperAndStatusAndCreatedAtBetween (Shipper shipper, String status, Date from, Date to, Pageable pageable);

    @Query("select o from Order o where " +
            "( :status is null or o.status = :status ) " +
            "and o.createdAt between :from and :to " +
            "and ( :phone is null or o.senderPhone = :phone) " +
            "and ( :shippingTime is null or o.shippingTime = :shippingTime ) " +
            "and ( :shippingType is null or o.shippingType = :shippingType ) " +
            "and ( :id is null or o.id = :id ) order by o.createdAt desc")
    Page<Order> findAllWithFilter(String status, Date from, Date to, String phone, Date shippingTime, String shippingType, Long id, Pageable pageable);

}
