package com.spring.app.price.controllers;

import com.spring.app.payload.MessageResponse;
import com.spring.app.price.models.Coupon;
import com.spring.app.price.models.repository.CouponRepository;
import com.spring.app.price.payload.request.CouponDataRequest;
import com.spring.app.price.payload.response.CouponDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/coupon")
public class CouponController {
    @Autowired
    CouponRepository couponRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCoupon() {
        List<Coupon> couponList = couponRepository.findAll();
        List<CouponDetailResponse> response = new ArrayList<>();

        for (Coupon c : couponList) {
            response.add(new CouponDetailResponse(
                    c.getId(),
                    c.getCode(),
                    c.getEnable(),
                    c.getRule(),
                    c.getValue(),
                    c.getValidFrom().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    c.getValidTo().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    c.getValidRoles(),
                    c.getCreatedAt().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    c.getUpdatedAt().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDateTime()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            ));
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveCoupon(@Valid @RequestBody CouponDataRequest couponData) {
        Coupon coupon;

        if (couponData.getId() != null) {
            coupon = this.couponRepository.findById(couponData.getId())
                    .orElseThrow(() -> new RuntimeException("Coupon not found!"));
        } else {
            coupon = new Coupon();
        }

        coupon.setCode(couponData.getCode())
                .setRule(couponData.getRule())
                .setValue(couponData.getValue())
                .setValidFrom(
                        Date.from(couponData.getValidFrom().atZone(ZoneId.systemDefault()).toInstant())
                ).setValidTo(
                        Date.from(couponData.getValidTo().atZone(ZoneId.systemDefault()).toInstant())
                ).setValidRoles(couponData.getValidRole())
                .setEnable(couponData.getEnable());

        this.couponRepository.save(coupon);
        return ResponseEntity.ok(new MessageResponse("Coupon save success!"));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getDetailById(@Valid @PathVariable Long id) {
        Coupon c = this.couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found!"));

        return ResponseEntity.ok(new CouponDetailResponse(
                c.getId(),
                c.getCode(),
                c.getEnable(),
                c.getRule(),
                c.getValue(),
                c.getValidFrom().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                c.getValidTo().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                c.getValidRoles(),
                c.getCreatedAt().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                c.getUpdatedAt().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        ));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable Long id) {
        Coupon c = this.couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found!"));

        couponRepository.delete(c);
        return ResponseEntity.ok(new MessageResponse("Coupon delete success!"));
    }
}
