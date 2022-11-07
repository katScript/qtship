package com.spring.app.products.payload;

public class PackageData {
    private Long id;
    private ProductData product;
    private Integer qty;
    private String createdAt;
    private String updatedAt;

    public PackageData() {}

    public PackageData(
            Long id,
            Integer qty,
            ProductData productData
    ) {
        this.id = id;
        this.qty = qty;
        this.product = productData;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductData getProduct() {
        return product;
    }

    public void setProduct(ProductData product) {
        this.product = product;
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

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
