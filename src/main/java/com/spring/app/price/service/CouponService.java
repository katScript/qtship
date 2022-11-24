package com.spring.app.price.service;

import com.spring.app.helper.date.DateFormatHelper;
import com.spring.app.price.models.Coupon;
import com.spring.app.price.models.repository.CouponRepository;
import com.spring.app.price.payload.CouponData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {
    @Autowired
    CouponRepository couponRepository;

    public Coupon processCouponData(CouponData data) {
        Coupon coupon;
        if (data.getId() != null) {
            coupon = couponRepository.findById(data.getId()).orElseThrow(() -> new RuntimeException("Coupon not found!"));
        } else {
            coupon = new Coupon();
        }

        coupon.setCode(data.getCode())
                .setRule(data.getRule())
                .setValue(data.getValue())
                .setValidFrom(
                        DateFormatHelper.stringToDate(data.getValidFrom())
                ).setValidTo(
                        DateFormatHelper.stringToDate(data.getValidTo())
                ).setValidRoles(data.getValidRoles())
                .setEnable(data.getEnable());

        return coupon;
    }

    public CouponData processCouponDataResponse(Coupon coupon) {
        CouponData couponData = new CouponData(
                coupon.getId(),
                coupon.getCode(),
                coupon.getEnable(),
                coupon.getRule(),
                coupon.getValue(),
                DateFormatHelper.dateToString(coupon.getValidFrom()),
                DateFormatHelper.dateToString(coupon.getValidTo()),
                coupon.getValidRoles()
        );

        couponData.setCreatedAt(
                DateFormatHelper.dateToString(coupon.getCreatedAt())
        );
        couponData.setUpdateAt(
                DateFormatHelper.dateToString(coupon.getUpdatedAt())
        );

        return couponData;
    }

}
