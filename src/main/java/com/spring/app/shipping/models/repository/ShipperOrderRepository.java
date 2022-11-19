package com.spring.app.shipping.models.repository;

import com.spring.app.orders.models.Order;
import com.spring.app.shipping.models.Shipper;
import com.spring.app.shipping.models.ShipperOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipperOrderRepository extends JpaRepository<ShipperOrder, Long> {
    List<ShipperOrder> findByOrder(Order order);
    List<ShipperOrder> findByShipper(Shipper shipper);
    @Query("select s from ShipperOrder s where s.shipper.id = ?1 and s.order.id = ?2")
    Optional<ShipperOrder> findByShipperAndOrder(Long shipperId, Long orderId);
}
