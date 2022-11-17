package com.spring.app.admin.payload;

public class AdminData {
    private Long id;
    private String userName;
    private Long userId;
    private String fullName;
    private String phone;
    private String address;
    private String currentAddress;
    private String createdAt;
    private String updatedAt;

    public AdminData() {}

    public AdminData(
            Long id,
            String userName,
            Long userId,
            String fullName,
            String phone,
            String address,
            String currentAddress
    ) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.currentAddress = currentAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
