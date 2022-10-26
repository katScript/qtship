package com.spring.app.customers.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Customer {
    private Long id;
    private String fullName;
    private String customerId;
    private String gender;
    private Date dob;
    private String phone;
    private String companyName;
    private String email;
    private String cidFront;
    private String cidBack;
    private Boolean subscription;
    private Date createdAt;
    private Date updatedAt;
    private Set<Address> addressSet;
    private Set<ForControl> forControls;

    private String username;

    public Customer() {}

    public Customer(com.spring.app.customers.models.Customer customer) {
        this.id = customer.getId();
        this.fullName = customer.getFullName();
        this.gender = customer.getGender();
        this.dob = customer.getDob();
        this.phone = customer.getPhone();
        this.companyName = customer.getCompanyName();
        this.email = customer.getEmail();
        this.cidFront = customer.getCidFront();
        this.cidBack = customer.getCidBack();
        this.subscription = customer.getSubscription();
        this.createdAt = customer.getCreatedAt();
        this.updatedAt = customer.getUpdatedAt();
        this.username = customer.getUser().getUsername();
        this.customerId = customer.getCustomerId();

        for (com.spring.app.customers.models.Address address : customer.getAddressSet())
            this.addAddress(new Address(address));

        for (com.spring.app.customers.models.ForControl forControl : customer.getForControls())
            this.addForControl(new ForControl(forControl));
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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

    public Set<Address> getAddressSet() {
        return addressSet;
    }

    public void setAddressSet(Set<Address> addressSet) {
        this.addressSet = addressSet;
    }

    public void addAddress(Address address) {
        if (this.addressSet == null) {
            this.addressSet = new HashSet<>();
        }

        this.addressSet.add(address);
    }

    public Set<ForControl> getForControls() {
        return forControls;
    }

    public void setForControls(Set<ForControl> forControls) {
        this.forControls = forControls;
    }

    public void addForControl(ForControl forControl) {
        if (this.forControls == null) {
            this.forControls = new HashSet<>();
        }

        this.forControls.add(forControl);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
