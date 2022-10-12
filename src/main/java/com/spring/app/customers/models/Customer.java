package com.spring.app.customers.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="full_name")
    private String fullName;

    @Column(name="gender")
    private String gender;

    @Column(name="dob")
    private Date dob;

    @Column(name="user_id")
    private Long userId;

    @Column(name="phone_number")
    private String phone;

    @Column(name="company_name")
    private String companyName;

    @Column(name="email")
    private String email;

    @Column(name="cid_front")
    private String cidFront;

    @Column(name="cid_back")
    private String cidBack;

    @Column(name="subscription")
    private Boolean subscription;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "customer")
    private Set<Address> addressSet = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<ForControl> forControls = new HashSet<>();

    public Customer() {}

    public Customer(
        String fullName,
        Long userId,
        String phone,
        String companyName,
        String email
    ) {
        this.fullName = fullName;
        this.userId = userId;
        this.phone = phone;
        this.companyName = companyName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Customer setFullName(String fullName) {
        this.fullName = fullName;

        return this;
    }

    public String getGender() {
        return gender;
    }

    public Customer setGender(String gender) {
        this.gender = gender;

        return this;
    }

    public Date getDob() {
        return dob;
    }

    public Customer setDob(Date dob) {
        this.dob = dob;

        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Customer setUserId(Long id) {
        this.userId = id;

        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Customer setPhone(String phone) {
        this.phone = phone;

        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Customer setCompanyName(String companyName) {
        this.companyName = companyName;

        return this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;

        return this;
    }

    public String getCidFront() {
        return cidFront;
    }

    public Customer setCidFront(String cidFront) {
        this.cidFront = cidFront;

        return this;
    }

    public String getCidBack() {
        return cidBack;
    }

    public Customer setCidBack(String cidBack) {
        this.cidBack = cidBack;

        return this;
    }

    public Boolean getSubscription() {
        return subscription;
    }

    public Customer setSubscription(Boolean subscription) {
        this.subscription = subscription;

        return this;
    }

    public Set<Address> getAddressSet() {
        return addressSet;
    }

    public Customer setAddressSet(Set<Address> addressSet) {
        this.addressSet = addressSet;
        return this;
    }

    public Set<ForControl> getForControls() {
        return forControls;
    }

    public Customer setForControls(Set<ForControl> forControls) {
        this.forControls = forControls;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
