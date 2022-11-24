package com.spring.app.price.payload.response;

public class ShippingFeeCalculateResponse {
    private Double shippingFee;

    public ShippingFeeCalculateResponse(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }
}
