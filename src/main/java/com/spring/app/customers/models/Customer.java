package com.spring.app.customers.models;

import com.spring.app.authentication.models.User;
import com.spring.app.orders.models.Order;
import com.spring.app.products.models.Product;
import com.spring.app.warehouse.models.Warehouse;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    public static final String ROLE = "customer";
    public static final String PREFIX = "CTM";
    public static final String GUEST_CODE = "GUEST";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "dob")
    private Date dob;
    @Column(name = "phone_number")
    private String phone;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "email")
    private String email;
    @Column(name = "cid_front")
    private String cidFront;
    @Column(name = "cid_back")
    private String cidBack;
    @Column(name = "subscription")
    private Boolean subscription;
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;
    @OneToMany(mappedBy = "customer",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Address> addressSet;
    @OneToMany(mappedBy = "customer",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ForControl> forControls;
    @OneToMany(mappedBy = "customer",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> orders;
    @OneToMany(mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Product> products;
    @OneToMany(mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Warehouse> warehouses;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Customer() {
        this.addressSet = new HashSet<>();
        this.forControls = new HashSet<>();
        this.orders = new HashSet<>();
        this.products = new HashSet<>();
        this.warehouses = new HashSet<>();
    }

    public Customer(
            String fullName,
            String gender,
            Date dob,
            String phone,
            String companyName,
            String email,
            String cidFront,
            String cidBack,
            Boolean subscription,
            Set<Address> addressSet,
            Set<ForControl> forControls,
            Set<Order> orders,
            Set<Product> products,
            Set<Warehouse> warehouses
    ) {
        this.fullName = fullName;
        this.phone = phone;
        this.companyName = companyName;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.cidFront = cidFront;
        this.cidBack = cidBack;
        this.subscription = subscription;
        this.addressSet = addressSet;
        this.forControls = forControls;
        this.orders = orders;
        this.products = products;
        this.warehouses = warehouses;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerId() {
        return PREFIX + String.format("%06d", id);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public Customer setOrders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }

    public Customer addOrders(Order order) {
        this.orders.add(order);
        return this;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Customer setProducts(Set<Product> products) {
        this.products = products;
        return this;
    }

    public Customer addProducts(Product product) {
        this.products.add(product);
        return this;
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

    public User getUser() {
        return user;
    }

    public Customer setUser(User user) {
        this.user = user;

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

    public Customer addAddress(Address address) {
        this.addressSet.add(address);
        return this;
    }

    public Set<ForControl> getForControls() {
        return forControls;
    }

    public Customer setForControls(Set<ForControl> forControls) {
        this.forControls = forControls;
        return this;
    }

    public Customer addForControls(ForControl forControl) {
        this.forControls.add(forControl);
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Set<Warehouse> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public Customer addWarehouse(Warehouse warehouse) {
        this.warehouses.add(warehouse);
        return this;
    }
}
