package com.spring.app.price.service;

import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.OrderItem;
import com.spring.app.price.models.Coupon;
import com.spring.app.price.models.repository.CouponRepository;
import com.spring.app.products.models.repository.ProductRepository;

public class PriceCalculate {
    private CouponRepository couponRepository;

    public PriceCalculate() {}
    public PriceCalculate(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }
    public Double processSubtotal(Order order) {
        Double subtotal = 0.0;
        Coupon coupon = couponRepository.findByCode(order.getCoupon()).orElse(null);

        for (OrderItem i : order.getOrderItemSet()) {

        }

        return subtotal;
    }
}
