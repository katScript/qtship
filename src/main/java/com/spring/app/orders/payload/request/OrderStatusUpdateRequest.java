package com.spring.app.orders.payload.request;

public class OrderStatusUpdateRequest {
    private Long id;
    private String status;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String code) {
        this.status = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
