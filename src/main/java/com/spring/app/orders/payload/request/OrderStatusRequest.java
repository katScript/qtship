package com.spring.app.orders.payload.request;

import java.util.List;

public class OrderStatusRequest {
    private Long id;
    private String code;
    private List<String> child;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getChild() {
        return child;
    }

    public void setChild(List<String> child) {
        this.child = child;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
