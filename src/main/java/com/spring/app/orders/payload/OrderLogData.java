package com.spring.app.orders.payload;

public class OrderLogData {
    private Long id;
    private Long orderId;
    private String detail;
    private String status;
    private String createdAt;
    private String updatedAt;

    public OrderLogData () {}

    public OrderLogData(
            Long id,
            Long orderId,
            String detail,
            String status,
            String createdAt,
            String updatedAt
    ) {
        this.id = id;
        this.orderId = orderId;
        this.detail = detail;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
