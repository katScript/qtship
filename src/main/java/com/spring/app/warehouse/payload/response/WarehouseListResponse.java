package com.spring.app.warehouse.payload.response;

public class WarehouseListResponse {
    private Long id;
    private String name;
    private String address;
    private String phone;

    public WarehouseListResponse() {}

    public WarehouseListResponse(
            Long id,
            String name,
            String address,
            String phone
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
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
}
