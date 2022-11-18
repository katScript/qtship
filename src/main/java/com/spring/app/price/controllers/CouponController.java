package com.spring.app.price.controllers;

import com.spring.app.payload.CustomPageResponse;
import com.spring.app.payload.FilterRequest;
import com.spring.app.payload.MessageResponse;
import com.spring.app.price.models.Coupon;
import com.spring.app.price.models.repository.CouponRepository;
import com.spring.app.price.payload.CouponData;
import com.spring.app.price.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/coupon")
public class CouponController {
    @Autowired
    CouponRepository couponRepository;
    @Autowired
    CouponService couponService;
    @GetMapping("/all")
    public ResponseEntity<?> getAllCoupon(
            @Valid FilterRequest fR
    ) {
        Pageable pageable = PageRequest.of(fR.getPage(), fR.getSize());
        Page<Coupon> couponList = couponRepository.findAll(pageable);
        List<CouponData> response = new ArrayList<>();
        CustomPageResponse pageResponse = new CustomPageResponse(couponList);

        for (Coupon c : couponList) {
            response.add(couponService.processCouponDataResponse(c));
        }

        pageResponse.setContent(response);
        return ResponseEntity.ok(pageResponse);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCoupon(@Valid @RequestBody CouponData couponData) {
        Coupon coupon = couponService.processCouponData(couponData);
        this.couponRepository.save(coupon);
        return ResponseEntity.ok(new MessageResponse("Coupon save success!"));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getDetailById(@Valid @PathVariable Long id) {
        Coupon c = this.couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found!"));

        return ResponseEntity.ok(couponService.processCouponDataResponse(c));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable Long id) {
        Coupon c = this.couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found!"));

        couponRepository.delete(c);
        return ResponseEntity.ok(new MessageResponse("Coupon delete success!"));
    }

    @GetMapping("/active/all")
    public ResponseEntity<?> getAllCouponValid() {
        List<Coupon> listCoupon = this.couponRepository.findCouponByEnable();
        List<CouponData> response = new ArrayList<>();

        for (Coupon c : listCoupon) {
            response.add(this.couponService.processCouponDataResponse(c));
        }

        return ResponseEntity.ok(response);
    }
}
