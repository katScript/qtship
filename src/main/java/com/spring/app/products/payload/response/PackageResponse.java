package com.spring.app.products.payload.response;

public class PackageResponse {
    private String sku;
    private Integer qty;

    public PackageResponse() {}

    public PackageResponse(
            String sku,
            Integer qty
    ) {
        this.sku = sku;
        this.qty = qty;
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
}
