package com.spring.app.products.payload;

public class ProductData {
    private Long id;
    private Long customerId;
    private String customerCode;
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

    public ProductData() {}

    public ProductData(
            Long id,
            Long customerId,
            String customerCode,
            String customerName,
            String sku,
            Integer qty,
            String name,
            Double weight,
            Double basePrice,
            Double publicPrice,
            String description
    ) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerCode = customerCode;
        this.sku = sku;
        this.qty = qty;
        this.name = name;
        this.weight = weight;
        this.basePrice = basePrice;
        this.publicPrice = publicPrice;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
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

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Double getPublicPrice() {
        return publicPrice;
    }

    public void setPublicPrice(Double publicPrice) {
        this.publicPrice = publicPrice;
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

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
