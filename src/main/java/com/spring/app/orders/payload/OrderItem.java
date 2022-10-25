package com.spring.app.orders.payload;


import com.spring.app.products.payload.response.ProductDetailResponse;

import java.util.ArrayList;
import java.util.List;

public class OrderItem {
    private Long id;
    private Double price;
    private ShippingAddress shippingAddress;
    private List<ProductDetailResponse> products;

    public OrderItem() {}

    public OrderItem(
            Long id,
            Double price,
            ShippingAddress shippingAddress,
            List<ProductDetailResponse> products
    ) {
        this.id = id;
        this.price = price;
        this.shippingAddress = shippingAddress;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<ProductDetailResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDetailResponse> products) {
        this.products = products;
    }

    public void addProduct(ProductDetailResponse product) {
        if (this.products.isEmpty()) {
            this.products = new ArrayList<>();
        }

        this.products.add(product);
    }
}
