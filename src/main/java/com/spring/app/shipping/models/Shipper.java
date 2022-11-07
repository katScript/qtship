package com.spring.app.shipping.models;

import com.spring.app.authentication.models.User;
import com.spring.app.orders.models.Order;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="shippers")
public class Shipper {
    public static final String ROLE = "shipper";
    public static final String PREFIX = "SPC";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "current_address")
    private String currentAddress;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;
    @OneToMany(mappedBy = "shipper",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> orders;

    public Shipper() {
        this.orders = new HashSet<>();
    }

    public Shipper(
        String fullName,
        String email,
        String phone,
        String address,
        String currentAddress,
        User user
    ) {
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.currentAddress = currentAddress;
        this.phone = phone;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Shipper setId(Long id) {
        this.id = id;
        return this;
    }
    

    public String getFullName() {
        return fullName;
    }

    public Shipper setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Shipper setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Shipper setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Shipper setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public Shipper setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Shipper setUser(User user) {
        this.user = user;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getShipperCode() {
        return PREFIX + String.format("%06d", id);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public Shipper setOrders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }
}
