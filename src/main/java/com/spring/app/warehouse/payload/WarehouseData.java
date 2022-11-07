package com.spring.app.warehouse.payload;

public class WarehouseData {
    private Long id;
    private String name;
    private String customerCode;
    private String address;
    private String phone;
    private String createdAt;
    private String updatedAt;

    public WarehouseData() {}

    public WarehouseData(
            Long id,
            String name,
            String address,
            String customerCode,
            String phone
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.customerCode = customerCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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
