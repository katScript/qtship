package com.spring.app.orders.payload.response;

import com.spring.app.products.payload.response.PackageResponse;
import com.spring.app.products.payload.response.ProductDetailResponse;
import com.spring.app.shipping.payload.response.ShippingAddressResponse;

import java.util.List;

public class OrderItemResponse {
    private Double price;
    private ShippingAddressResponse shippingAddress;
    private List<ProductDetailResponse> products;

    public OrderItemResponse() {}
    public OrderItemResponse(
            Double price,
            ShippingAddressResponse shippingAddress,
            List<ProductDetailResponse> products
    ) {
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

    public List<ProductDetailResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDetailResponse> products) {
        this.products = products;
    }
}
