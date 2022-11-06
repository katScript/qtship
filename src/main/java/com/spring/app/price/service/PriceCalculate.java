package com.spring.app.price.service;

import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.OrderItem;
import com.spring.app.price.models.Coupon;
import com.spring.app.price.models.repository.CouponRepository;
import com.spring.app.products.models.Package;

public class PriceCalculate {
    private CouponRepository couponRepository;

    public PriceCalculate(
            CouponRepository couponRepository
    ) {
        this.couponRepository = couponRepository;
    }

    public void processSubtotal(Order order) {
        Double subtotal = 0.0;
        Coupon coupon = couponRepository.findByCode(order.getCoupon()).orElse(null);

        for (OrderItem i : order.getOrderItemSet()) {
            Double price = 0.0;

            for (Package p: i.getPackages()) {
                price += p.getQty() * p.getProduct().getPublicPrice();
            }

            subtotal += price;
            i.setPrice(price);
        }

        if (coupon != null && coupon.isValid()) {
            if (coupon.getRule().equals(Coupon.BASE)) {
                subtotal -= coupon.getValue();
            } else if (coupon.getRule().equals(Coupon.PERCENT)) {
                subtotal -= subtotal * (coupon.getValue() / 100);
            }
        }

        // distance calculate

        order.setSubtotal(subtotal);
    }
}
