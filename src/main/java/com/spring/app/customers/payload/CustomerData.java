package com.spring.app.customers.payload;

import com.spring.app.customers.models.Customer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CustomerData {
    private Long id;
    private String fullName;
    private String username;
    private String customerId;
    private String gender;
    private String dob;
    private String phone;
    private String companyName;
    private String email;
    private String cidFront;
    private String cidBack;
    private Boolean subscription;
    private String createdAt;
    private String updatedAt;
    private Set<AddressData> addressSet;
    private Set<ForControlData> forControls;

    public CustomerData() {
        this.addressSet = new HashSet<>();
        this.forControls = new HashSet<>();
    }

    public CustomerData(
            Long id,
            String customerId,
            String username,
            String fullName,
            String gender,
            String dob,
            String phone,
            String companyName,
            String email,
            String cidFront,
            String cidBack,
            Boolean subscription,
            Set<AddressData> addressSet,
            Set<ForControlData> forControls
    ) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.companyName = companyName;
        this.email = email;
        this.cidBack = cidBack;
        this.cidFront = cidFront;
        this.subscription = subscription;
        this.addressSet = addressSet;
        this.forControls = forControls;
        this.customerId = customerId;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidFront() {
        return cidFront;
    }

    public void setCidFront(String cidFront) {
        this.cidFront = cidFront;
    }

    public String getCidBack() {
        return cidBack;
    }

    public void setCidBack(String cidBack) {
        this.cidBack = cidBack;
    }

    public Boolean getSubscription() {
        return subscription;
    }

    public void setSubscription(Boolean subscription) {
        this.subscription = subscription;
    }

    public Set<AddressData> getAddressSet() {
        return addressSet;
    }

    public void setAddressSet(Set<AddressData> addressSet) {
        this.addressSet = addressSet;
    }

    public void addAddress(AddressData address) {
        this.addressSet.add(address);
    }

    public Set<ForControlData> getForControls() {
        return forControls;
    }

    public void setForControls(Set<ForControlData> forControls) {
        this.forControls = forControls;
    }

    public void addForControl(ForControlData forControl) {
        this.forControls.add(forControl);
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
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
