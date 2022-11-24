package com.spring.app.price.service;

import com.spring.app.orders.models.Order;
import com.spring.app.price.models.Coupon;
import com.spring.app.price.models.repository.CouponRepository;
import com.spring.app.products.models.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PriceCalculate {
    @Autowired
    private CouponRepository couponRepository;

    public void processSubtotal(Order order) {
        Double subtotal = 0.0;

        for (Package item : order.getPackages()) {
            subtotal += item.getQty() * item.getPrice();
        }

        this.processShippingFee(order);
        subtotal += order.getShippingFee();

        order.setSubtotal(subtotal);
    }

    public void processShippingFee(Order order) {
        int power = (int) Math.max(Math.round(order.getTotalWeight() / 0.5) - 1, 0);
        String provinceId = order.getShippingAddress().getProvinceId();

        // find type price by province
        String type = "A";

        // get rule of type
        String rulePrice = "23.800,27.500,31.800,35.800";
        List<String> rules = new ArrayList<>(Arrays.asList(rulePrice.split(",")));
        int maxBoundary = rules.size() - 1;
        double maxBoundaryPrice = Double.parseDouble(rules.get(maxBoundary)),
                // get advance price from config
                advancePrice = 5.500;

        Double shippingFee;

        if (power <= maxBoundary) {
            shippingFee = Double.parseDouble(rules.get(power));
        } else {
            shippingFee = maxBoundaryPrice;

            for (int i = maxBoundary; i < power; ++i) {
                shippingFee += advancePrice;
            }
        }

        // advance fee

        // get from admin config
        double VAT = 0.08,
                fuel = 0.15,
                weightLimit = 10;

        shippingFee += shippingFee * VAT + shippingFee * fuel;

        if (order.getTotalWeight() >= weightLimit) {
            // get price advance in config
            shippingFee += shippingFee * 0.2;
        }

        Coupon coupon = couponRepository.findByCode(order.getCoupon()).orElse(null);

        if (coupon != null && coupon.isValid()) {
            if (coupon.getRule().equals(Coupon.BASE)) {
                shippingFee -= coupon.getValue();
            } else if (coupon.getRule().equals(Coupon.PERCENT)) {
                shippingFee -= shippingFee * (coupon.getValue() / 100);
            }
        }

        order.setShippingFee(shippingFee);
    }
}
