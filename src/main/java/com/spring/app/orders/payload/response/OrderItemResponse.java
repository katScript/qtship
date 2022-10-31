package com.spring.app.orders.payload.response;

import com.spring.app.products.payload.response.PackageResponse;
import com.spring.app.shipping.payload.response.ShippingAddressResponse;

import java.util.List;

public class OrderItemResponse {
    private Double price;
    private ShippingAddressResponse shippingAddressResponse;
    private List<PackageResponse> products;

    public OrderItemResponse() {}
    public OrderItemResponse(
            Double price,
            ShippingAddressResponse shippingAddressResponse,
            List<PackageResponse> products
    ) {
        this.price = price;
        this.shippingAddressResponse = shippingAddressResponse;
        this.products = products;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ShippingAddressResponse getShippingAddressResponse() {
        return shippingAddressResponse;
    }

    public void setShippingAddressResponse(ShippingAddressResponse shippingAddressResponse) {
        this.shippingAddressResponse = shippingAddressResponse;
    }

    public List<PackageResponse> getProducts() {
        return products;
    }

    public void setProducts(List<PackageResponse> products) {
        this.products = products;
    }
}
