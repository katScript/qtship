package com.spring.app.orders.payload.response;

import com.spring.app.products.payload.response.PackageResponse;
import com.spring.app.products.payload.response.ProductDetailResponse;
import com.spring.app.shipping.payload.response.ShippingAddressResponse;

import java.util.List;

public class OrderItemResponse {
    private Long id;
    private Double price;
    private ShippingAddressResponse shippingAddress;
    private List<PackageResponse> products;

    public OrderItemResponse() {}
    public OrderItemResponse(
            Long id,
            Double price,
            ShippingAddressResponse shippingAddress,
            List<PackageResponse> products
    ) {
        this.id = id;
        this.price = price;
        this.shippingAddress = shippingAddress;
        this.products = products;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ShippingAddressResponse getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddressResponse shippingAddressResponse) {
        this.shippingAddress = shippingAddressResponse;
    }

    public List<PackageResponse> getProducts() {
        return products;
    }

    public void setProducts(List<PackageResponse> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
