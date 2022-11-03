package com.spring.app.products.payload.response;

import java.util.Date;

public class ProductDetailResponse {
    private Long id;
    private String customerId;
    private String customerName;
    private String sku;
    private Integer qty;
    private String name;
    private Double weight;
    private Double basePrice;
    private Double publicPrice;
    private String description;
    private String image;
    private Date createdAt;
    private Date updatedAt;

    public ProductDetailResponse() {}

    public ProductDetailResponse(
            Long id,
            String customerId,
            String customerName,
            String sku,
            Integer qty,
            String name,
            Double weight,
            Double basePrice,
            Double publicPrice,
            String description,
            Date createdAt,
            Date updatedAt
    ) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.sku = sku;
        this.qty = qty;
        this.name = name;
        this.weight = weight;
        this.basePrice = basePrice;
        this.publicPrice = publicPrice;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPublicPrice() {
        return publicPrice;
    }

    public void setPublicPrice(Double publicPrice) {
        this.publicPrice = publicPrice;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdDate) {
        this.createdAt = createdDate;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedDate) {
        this.updatedAt = updatedDate;
    }
}
