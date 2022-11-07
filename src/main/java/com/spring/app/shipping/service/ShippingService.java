package com.spring.app.shipping.service;

import com.spring.app.helper.services.DateFormatHelper;
import com.spring.app.shipping.models.Shipper;
import com.spring.app.shipping.models.ShippingAddress;
import com.spring.app.shipping.models.repository.ShipperRepository;
import com.spring.app.shipping.payload.ShipperData;
import com.spring.app.shipping.payload.ShippingAddressData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {
    @Autowired
    ShipperRepository shipperRepository;
    public Shipper processShipperData(ShipperData data) {
        Shipper shipper;
        if (data.getId() != null) {
            shipper = shipperRepository.findById(data.getId()).orElseThrow(() -> new RuntimeException("Shipper not found!"));
        } else {
            shipper = new Shipper();
        }

        shipper.setFullName(data.getFullName())
                .setPhone(data.getPhone())
                .setAddress(data.getAddress())
                .setCurrentAddress(data.getCurrentAddress())
                .setEmail(data.getEmail());

        return shipper;
    }

    public ShipperData processShipperDataResponse(Shipper shipper) {
        ShipperData shipperData = new ShipperData(
                shipper.getId(),
                shipper.getShipperCode(),
                shipper.getUser().getUsername(),
                shipper.getFullName(),
                shipper.getEmail(),
                shipper.getPhone(),
                shipper.getAddress(),
                shipper.getCurrentAddress()
        );

        shipperData.setCreatedAt(
                DateFormatHelper.dateToString(shipper.getCreatedAt())
        );
        shipperData.setUpdatedAt(
                DateFormatHelper.dateToString(shipper.getUpdatedAt())
        );

        return shipperData;
    }

    public ShippingAddressData processShippingAddressResponse(ShippingAddress shippingAddress) {
        return new ShippingAddressData(
                shippingAddress.getId(),
                shippingAddress.getName(),
                shippingAddress.getPhone(),
                shippingAddress.getProvince(),
                shippingAddress.getProvinceId(),
                shippingAddress.getDistrict(),
                shippingAddress.getDistrictId(),
                shippingAddress.getWard(),
                shippingAddress.getWardId(),
                shippingAddress.getStreet()
        );
    }
}