package com.spring.app.shipping.models.repository;

import com.spring.app.orders.models.Order;
import com.spring.app.shipping.models.Shipper;
import com.spring.app.shipping.models.ShipperOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipperOrderRepository extends JpaRepository<ShipperOrder, Long> {
    List<ShipperOrder> findByOrder(Order order);
    List<ShipperOrder> findByShipper(Shipper shipper);
}
