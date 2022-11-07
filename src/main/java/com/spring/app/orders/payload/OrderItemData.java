package com.spring.app.orders.payload;

import com.spring.app.products.payload.PackageData;
import com.spring.app.shipping.payload.ShippingAddressData;

import java.util.List;

public class OrderItemData {
    private Long id;
    private Double price;
    private ShippingAddressData shippingAddress;
    private List<PackageData> products;

    public OrderItemData() {}

    public OrderItemData(
            Long id,
            Double price,
            ShippingAddressData shippingAddress,
            List<PackageData> products
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

    public ShippingAddressData getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddressData shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<PackageData> getProducts() {
        return products;
    }

    public void setProducts(List<PackageData> products) {
        this.products = products;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
