package com.spring.app.products.payload.response;

public class PackageResponse {
    private Long id;
    private String sku;
    private Integer qty;

    public PackageResponse() {}

    public PackageResponse(
            Long id,
            String sku,
            Integer qty
    ) {
        this.sku = sku;
        this.qty = qty;
        this.id = id;
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
}
