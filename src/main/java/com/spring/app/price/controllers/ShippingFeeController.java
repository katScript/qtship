package com.spring.app.price.controllers;

import com.spring.app.office.models.Office;
import com.spring.app.office.models.repository.OfficeRepository;
import com.spring.app.payload.MessageResponse;
import com.spring.app.price.models.Coupon;
import com.spring.app.price.models.repository.CouponRepository;
import com.spring.app.price.payload.request.ShippingFeeCalculate;
import com.spring.app.price.payload.response.ShippingFeeCalculateResponse;
import com.spring.app.price.service.PriceCalculate;
import com.spring.app.warehouse.models.Warehouse;
import com.spring.app.warehouse.models.repository.WarehouseRepository;
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

    @Autowired
    WarehouseRepository warehouseRepository;

    @PostMapping("/fee/calculate")
    public ResponseEntity<?> getShippingFee(@Valid @RequestBody ShippingFeeCalculate rq) {
        try {
            Office office = null;
            if (rq.getOfficeId() != null) {
                office = officeRepository.findById(rq.getOfficeId())
                        .orElseThrow(() -> new RuntimeException("Office not found!"));
            } else if (rq.getWarehouseId() != null) {
                Warehouse warehouse = warehouseRepository.findById(rq.getWarehouseId())
                        .orElseThrow(() -> new RuntimeException("Warehouse not found!"));

                office = officeRepository.findFirstByProvinceId(warehouse.getProvinceId())
                        .orElseThrow(() -> new RuntimeException("Office not found!"));
            }

            Double shippingFee = priceCalculate.calculateShippingFee(
                    office,
                    rq.getProvinceId(),
                    rq.getWeight()
            );

            if (rq.getCouponCode() != null) {
                Coupon coupon = couponRepository.findByCode(rq.getCouponCode())
                        .orElseThrow(() -> new RuntimeException("Coupon code not found!"));

                shippingFee -= priceCalculate.applyCoupon(shippingFee, coupon);
            }

            return ResponseEntity.ok(new ShippingFeeCalculateResponse(shippingFee));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new ShippingFeeCalculateResponse(0.0));
        }
    }
}
