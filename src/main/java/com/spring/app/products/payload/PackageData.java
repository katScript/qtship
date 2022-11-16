package com.spring.app.products.payload;

public class PackageData {
    private Long id;
    private ProductData product;
    private String name;
    private Integer qty;
    private Double price;
    private Double weight;
    private String image;
    private String createdAt;
    private String updatedAt;

    public PackageData() {}

    public PackageData(
            Long id,
            ProductData product,
            String name,
            Integer qty,
            Double price,
            Double weight,
            String image
    ) {
        this.id = id;
        this.product = product;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.weight = weight;
        this.image = image;
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

    public void setProduct(ProductData productId) {
        this.product = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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
