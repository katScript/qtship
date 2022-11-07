package com.spring.app.products.payload;

public class PackageData {
    private Long id;
    private ProductData productData;
    private String createdAt;
    private String updatedAt;

    public PackageData() {}

    public PackageData(
            Long id,
            ProductData productData
    ) {
        this.id = id;
        this.productData = productData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductData getProductData() {
        return productData;
    }

    public void setProductData(ProductData productData) {
        this.productData = productData;
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
