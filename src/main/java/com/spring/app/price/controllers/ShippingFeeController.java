package com.spring.app.price.controllers;

import com.spring.app.office.models.Office;
import com.spring.app.office.models.repository.OfficeRepository;
import com.spring.app.payload.MessageResponse;
import com.spring.app.price.models.Coupon;
import com.spring.app.price.models.repository.CouponRepository;
import com.spring.app.price.payload.request.ShippingFeeCalculate;
import com.spring.app.price.payload.response.ShippingFeeCalculateResponse;
import com.spring.app.price.service.PriceCalculate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/shipping")
public class ShippingFeeController {
    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    PriceCalculate priceCalculate;

    @GetMapping("/fee/calculate")
    public ResponseEntity<?> getShippingFee(@Valid @RequestBody ShippingFeeCalculate rq) {
        try {
            Office office = officeRepository.findById(rq.getOfficeId())
                    .orElseThrow(() -> new RuntimeException("Office not found!"));
            Coupon coupon = couponRepository.findByCode(rq.getCouponCode())
                    .orElseThrow(() -> new RuntimeException("Coupon code not found!"));

            Double shippingFee = priceCalculate.calculateShippingFee(
                    office,
                    rq.getProvinceId(),
                    rq.getWeight()
            );

            shippingFee = priceCalculate.applyCoupon(shippingFee, coupon);
            return ResponseEntity.ok(new ShippingFeeCalculateResponse(shippingFee));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
