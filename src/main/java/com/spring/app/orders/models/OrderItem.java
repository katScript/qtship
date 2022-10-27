package com.spring.app.orders.models;


import com.spring.app.customers.models.Customer;
import com.spring.app.products.models.Package;
import com.spring.app.products.models.Product;
import com.spring.app.shipping.models.ShippingAddress;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="orders_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "price")
    private Double price;
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="order_id", referencedColumnName = "id", nullable = false)
    private Order order;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_id", referencedColumnName = "id")
    private ShippingAddress shippingAddress;
    @OneToMany(mappedBy = "orderItem",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Package> packages = new HashSet<>();

    public OrderItem() {}

    public OrderItem(
            Double price,
            Order order,
            ShippingAddress shippingAddress,
            Set<Package> packages
    ) {
        this.price = price;
        this.order = order;
        this.shippingAddress = shippingAddress;
        this.packages = packages;
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public OrderItem setOrder(Order order) {
        this.order = order;
        return this;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public OrderItem setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public OrderItem setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Set<Package> getPackages() {
        return packages;
    }

    public OrderItem setPackages(Set<Package> packages) {
        this.packages = packages;
        return this;
    }

    public OrderItem addPackages(Package packageData) {
        this.packages.add(packageData);
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
