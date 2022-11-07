package com.spring.app.orders.payload;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusData {
    private Long id;
    private String code;
    private List<String> child;

    public OrderStatusData() {
        this.child = new ArrayList<>();
    }

    public OrderStatusData(
            Long id,
            String code,
            List<String> child
    ) {
        this.id = id;
        this.code = code;
        this.child = child;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
