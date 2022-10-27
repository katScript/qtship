package com.spring.app.orders.payload.request;

import com.spring.app.products.payload.request.PackageDataRequest;
import com.spring.app.shipping.payload.request.ShippingAddressRequest;

import java.util.List;

public class OrderItemRequest {
    private Long id;
    private ShippingAddressRequest shippingAddress;
    private List<PackageDataRequest> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShippingAddressRequest getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddressRequest shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<PackageDataRequest> getProducts() {
        return products;
    }

    public void setProducts(List<PackageDataRequest> products) {
        this.products = products;
    }
}
