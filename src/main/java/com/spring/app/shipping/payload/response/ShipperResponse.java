package com.spring.app.shipping.payload.response;

import java.util.Date;

public class ShipperResponse {
    private Long id;
    private String shipperCode;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String currentAddress;
    private String createdAt;

    public ShipperResponse() {}

    public ShipperResponse(
            Long id,
            String shipperCode,
            String fullName,
            String email,
            String phone,
            String address,
            String currentAddress,
            String createdAt
    ) {
        this.id = id;
        this.shipperCode = shipperCode;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.currentAddress = currentAddress;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShipperCode() {
        return shipperCode;
    }

    public void setShipperCode(String shipperCode) {
        this.shipperCode = shipperCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
