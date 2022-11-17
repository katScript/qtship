package com.spring.app.shipping.payload;

import com.spring.app.orders.payload.OrderData;
import com.spring.app.shipping.models.ShipperOrder;

public class ShipperOrderData {
    private Long id;
    private ShipperData shipper;
    private OrderData order;
    private String createdAt;
    private String updatedAt;

    public ShipperOrderData(
            Long id,
            ShipperData shipper,
            OrderData order
    ) {
        this.id = id;
        this.shipper = shipper;
        this.order = order;
    }

    public ShipperData getShipper() {
        return shipper;
    }

    public void setShipper(ShipperData shipper) {
        this.shipper = shipper;
    }

    public OrderData getOrder() {
        return order;
    }

    public void setOrder(OrderData order) {
        this.order = order;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
