package com.spring.app.products.models;

import com.spring.app.customers.models.Customer;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "sku")
    private String sku;
    @Column(name = "qty")
    private Integer qty;
    @Column(name = "name")
    private String name;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "base_price")
    private Double basePrice;
    @Column(name = "pub_price")
    private Double publicPrice;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "is_guest")
    private Boolean isGuest = false;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public Product() {}

    public Product(
        String sku,
        String name,
        Integer qty,
        Double weight,
        Double basePrice,
        Double publicPrice,
        String description,
        String image,
        Customer customer
    ) {
        this.sku = sku;
        this.qty = qty;
        this.name = name;
        this.weight = weight;
        this.basePrice = basePrice;
        this.publicPrice = publicPrice;
        this.description = description;
        this.image = image;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public Product setSku(String sku) {
        this.sku = sku;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getQty() {
        return qty;
    }

    public Product setQty(Integer qty) {
        this.qty = qty;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public Product setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public Product setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public Double getPublicPrice() {
        return publicPrice;
    }

    public Product setPublicPrice(Double publicPrice) {
        this.publicPrice = publicPrice;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getGuest() {
        return isGuest;
    }

    public void setGuest(Boolean guest) {
        isGuest = guest;
    }
}
