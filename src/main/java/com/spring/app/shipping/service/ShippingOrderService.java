package com.spring.app.shipping.service;

import com.spring.app.helper.date.DateFormatHelper;
import com.spring.app.orders.services.OrderService;
import com.spring.app.shipping.models.Shipper;
import com.spring.app.shipping.models.ShipperOrder;
import com.spring.app.shipping.models.repository.ShipperOrderRepository;
import com.spring.app.shipping.models.repository.ShipperRepository;
import com.spring.app.shipping.payload.ShipperOrderData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingOrderService {
    @Autowired
    ShippingService shippingService;
    @Autowired
    OrderService orderService;
    @Autowired
    ShipperRepository shipperRepository;
    @Autowired
    ShipperOrderRepository shipperOrderRepository;

    public ShipperOrderData processShipperOrderDataResponse(ShipperOrder shipperOrder) {
        ShipperOrderData shipperOrderData = new ShipperOrderData(
                shipperOrder.getId(),
                shippingService.processShipperDataResponse(shipperOrder.getShipper()),
                orderService.getOrderDetail(shipperOrder.getOrder())
        );

        shipperOrderData.setCreatedAt(
                DateFormatHelper.dateToString(shipperOrder.getCreatedAt())
        );
        shipperOrderData.setUpdatedAt(
                DateFormatHelper.dateToString(shipperOrder.getUpdatedAt())
        );

        return shipperOrderData;
    }

    public List<ShipperOrderData> getListOrderAssign(Long id) {
        Shipper shipper = shipperRepository.findById(id).orElse(null);
        List<ShipperOrder> orderData;
        List<ShipperOrderData> listData = new ArrayList<>();

        if (shipper != null) {
            orderData = shipperOrderRepository.findByShipper(shipper);

            for (ShipperOrder so: orderData) {
                listData.add(processShipperOrderDataResponse(so));
            }
        }

        return listData;
    }
}
