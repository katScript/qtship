package com.spring.app.products.payload.response;

public class PackageResponse {
    private Long id;
    private String sku;
    private Integer qty;
    private String name;
    private Double weight;
    private Double publicPrice;

    public PackageResponse() {}

    public PackageResponse(
            Long id,
            String sku,
            Integer qty,
            String name,
            Double weight,
            Double publicPrice
    ) {
        this.sku = sku;
        this.qty = qty;
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.publicPrice = publicPrice;
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
}
