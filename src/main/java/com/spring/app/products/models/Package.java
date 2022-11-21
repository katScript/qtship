package com.spring.app.products.models;

import com.spring.app.orders.models.Order;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="orders_package")
public class Package {
    public static final String PREFIX = "PCK";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="order_id", referencedColumnName = "id", nullable = false)
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    @Column(name = "name")
    private String name;
    @Column(name = "qty")
    private Integer qty;
    @Column(name = "price")
    private Double price;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "long")
    private Double longPackage;
    @Column(name = "width")
    private Double widthPackage;
    @Column(name = "height")
    private Double heightPackage;
    @Column(name = "special_type")
    private Boolean specialType;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public Package() {}

    public Package (
            Order order,
            Product product,
            String name,
            Integer qty,
            Double price,
            Double weight,
            String imageUrl
    ) {
        this.order = order;
        this.product = product;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.weight = weight;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getPackageCode() {
        return PREFIX + String.format("%06d", id);
    }

    public Package setId(Long id) {
        this.id = id;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public Package setOrder(Order order) {
        this.order = order;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public Package setProduct(Product product) {
        this.product = product;
        return this;
    }

    public String getName() {
        return name;
    }

    public Package setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getQty() {
        return qty;
    }

    public Package setQty(Integer qty) {
        this.qty = qty;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Package setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public Package setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Package setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Double getLongPackage() {
        return longPackage;
    }

    public Package setLongPackage(Double longPackage) {
        this.longPackage = longPackage;
        return this;
    }

    public Double getWidthPackage() {
        return widthPackage;
    }

    public Package setWidthPackage(Double widthPackage) {
        this.widthPackage = widthPackage;
        return this;
    }

    public Double getHeightPackage() {
        return heightPackage;
    }

    public Package setHeightPackage(Double heightPackage) {
        this.heightPackage = heightPackage;
        return this;
    }

    public Boolean getSpecialType() {
        return specialType;
    }

    public Package setSpecialType(Boolean specialType) {
        this.specialType = specialType;
        return this;
    }
}
