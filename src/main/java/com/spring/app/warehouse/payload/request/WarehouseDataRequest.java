package com.spring.app.warehouse.payload.request;

import com.spring.app.warehouse.payload.WarehouseData;

public class WarehouseDataRequest {
    private WarehouseData data;
    private Long customerId;

    public WarehouseData getData() {
        return data;
    }

    public void setData(WarehouseData data) {
        this.data = data;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
