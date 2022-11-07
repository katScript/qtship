package com.spring.app.products.payload.response;

import java.util.Date;

public class PackageResponse {
    private Long id;
    private Long productId;
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
    private String createdAt;
    private String updatedAt;

    public PackageResponse() {}

    public PackageResponse(
            Long id,
            Long productId,
            String customerId,
            String customerName,
            String sku,
            Integer qty,
            String name,
            Double weight,
            Double basePrice,
            Double publicPrice,
            String description
    ) {
        this.sku = sku;
        this.qty = qty;
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.publicPrice = publicPrice;
        this.productId = productId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.basePrice = basePrice;
        this.description = description;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPublicPrice() {
        return publicPrice;
    }

    public void setPublicPrice(Double publicPrice) {
        this.publicPrice = publicPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
