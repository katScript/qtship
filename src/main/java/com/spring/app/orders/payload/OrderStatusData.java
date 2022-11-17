package com.spring.app.orders.payload;

import java.util.ArrayList;
import java.util.List;

public class OrderStatusData {
    private Long id;
    private String code;
    private String label;
    private Boolean enable;
    private List<String> child;
    private String createdAt;
    private String updatedAt;

    public OrderStatusData() {
        this.child = new ArrayList<>();
    }

    public OrderStatusData(
            Long id,
            String code,
            String label,
            Boolean enable,
            List<String> child
    ) {
        this.id = id;
        this.code = code;
        this.label = label;
        this.enable = enable;
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
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
}
