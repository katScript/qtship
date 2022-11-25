package com.spring.app.price.service;

import com.spring.app.admin.config.models.AdminConfig;
import com.spring.app.admin.config.models.repository.AdminConfigRepository;
import com.spring.app.admin.config.service.AdminConfigService;
import com.spring.app.office.models.Office;
import com.spring.app.orders.models.Order;
import com.spring.app.price.models.Coupon;
import com.spring.app.price.models.Rule;
import com.spring.app.price.models.RuleArea;
import com.spring.app.price.models.RulePriceList;
import com.spring.app.price.models.repository.CouponRepository;
import com.spring.app.price.models.repository.RuleAreaRepository;
import com.spring.app.price.models.repository.RulePriceListRepository;
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

    @Autowired
    private RuleAreaRepository ruleAreaRepository;

    @Autowired
    private RulePriceListRepository rulePriceListRepository;

    @Autowired
    private AdminConfigRepository adminConfigRepository;

    @Autowired
    private AdminConfigService adminConfigService;

    public void processSubtotal(Order order) {
        Double subtotal = 0.0;

        for (Package item : order.getPackages()) {
            subtotal += item.getQty() * item.getPrice();
        }

        processShippingFee(order);
        subtotal += order.getShippingFee();

        order.setSubtotal(subtotal);
    }

    public void processShippingFee(Order order) {
        Office office = order.getOffice();
        Coupon coupon = couponRepository.findByCode(order.getCoupon()).orElse(null);

        Double shippingFee = calculateShippingFee(
                office,
                order.getShippingAddress().getProvinceId(),
                order.getTotalWeight()
        );

        Double couponFee = applyCoupon(shippingFee, coupon);

        order.setShippingFee(shippingFee)
                .setCouponFee(couponFee);
    }

    public Double calculateShippingFee(Office office, String provinceId, Double weight) {
        Double shippingFee = 0.0;

        if (office != null) {
            Rule rule = office.getPriceRule();
            RuleArea area = ruleAreaRepository.findFirstByRuleAndProvinceId(rule, provinceId)
                    .orElseThrow(() -> new RuntimeException("Can not find area!"));

            RulePriceList priceList = rulePriceListRepository.findFirstByRuleAndAreaCode(rule, area.getAreaCode())
                    .orElseThrow(() -> new RuntimeException("Can not get price list!"));

            int factor = (int) Math.max(Math.round(weight / priceList.getWeightFactor()) - 1, 0);

            int maxBoundary = priceList.getPriceList().size();
            double advancePrice = priceList.getAdvancePrice();

            if (factor < maxBoundary) {
                shippingFee = priceList.getPriceList().get(factor);
            } else {
                shippingFee = priceList.getPriceList().get(maxBoundary - 1);

                for (int i = maxBoundary; i <= factor; ++i) {
                    shippingFee += advancePrice;
                }
            }

            AdminConfig VAT = adminConfigService.getFeeConfig("VAT"),
                    fuel = adminConfigService.getFeeConfig("fuel"),
                    weightLimit = adminConfigService.getFeeConfig("weight_limit"),
                    advanceFeeWeightLimit = adminConfigService.getFeeConfig("advance_fee_weight");

            shippingFee += shippingFee * Double.parseDouble(VAT.getValue()) +
                    shippingFee * Double.parseDouble(fuel.getValue());

            if (weight >= Double.parseDouble(weightLimit.getValue())) {
                shippingFee += shippingFee * Double.parseDouble(advanceFeeWeightLimit.getValue()) / 100;
            }
        }

        return shippingFee;
    }

    public Double applyCoupon(Double shippingFee, Coupon coupon) {
        if (shippingFee != 0.0 && coupon != null && coupon.isValid()) {
            Double couponFee = 0.0;
            if (coupon.getRule().equals(Coupon.BASE)) {
                couponFee = coupon.getValue();
            } else if (coupon.getRule().equals(Coupon.PERCENT)) {
                couponFee = shippingFee * coupon.getValue() / 100;
            }

            return Math.min(shippingFee, couponFee);
        }

        return 0.0;
    }
}
