package com.spring.app.shipping.service;

import com.spring.app.helper.services.DateFormatHelper;
import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.repository.OrderRepository;
import com.spring.app.orders.services.OrderService;
import com.spring.app.orders.services.OrderStatusService;
import com.spring.app.shipping.models.Shipper;
import com.spring.app.shipping.models.ShipperOrder;
import com.spring.app.shipping.models.ShippingAddress;
import com.spring.app.shipping.models.repository.ShipperOrderRepository;
import com.spring.app.shipping.models.repository.ShipperRepository;
import com.spring.app.shipping.payload.ShipperData;
import com.spring.app.shipping.payload.ShipperOrderData;
import com.spring.app.shipping.payload.ShippingAddressData;
import com.spring.app.shipping.payload.request.AssignOrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingService {
    @Autowired
    ShipperRepository shipperRepository;
    @Autowired
    ShipperOrderRepository shipperOrderRepository;
    @Autowired
    OrderRepository orderRepository;
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

    public void acceptOrder(AssignOrderRequest data) {
        Order order = orderRepository.findById(data.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found"));
        Shipper shipper = shipperRepository.findById(data.getShipperId()).orElseThrow(() -> new RuntimeException("Shipper not found"));
        List<ShipperOrder> shipperOrders = shipperOrderRepository.findByShipperAndOrder(shipper, order);

        if (!shipperOrders.isEmpty()) {
            ShipperOrder shipperOrder = shipperOrders.get(0);

            order.setShipper(shipper)
                    .setStatus(OrderStatusService.SHIPPER_CONFIRMED);

            orderRepository.save(order);
            shipperOrderRepository.delete(shipperOrder);
        }
    }

    public void rejectOrder(AssignOrderRequest data) {
        Order order = orderRepository.findById(data.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found"));
        Shipper shipper = shipperRepository.findById(data.getShipperId()).orElseThrow(() -> new RuntimeException("Shipper not found"));
        List<ShipperOrder> shipperOrder = shipperOrderRepository.findByShipperAndOrder(shipper, order);

        if (!shipperOrder.isEmpty()) {
            shipperOrderRepository.delete(shipperOrder.get(0));
        }
    }
}
