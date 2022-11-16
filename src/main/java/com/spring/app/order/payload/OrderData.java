package com.spring.app.order.payload;

import com.spring.app.orders.payload.OrderItemData;
import com.spring.app.shipping.payload.ShipperData;
import com.spring.app.shipping.payload.ShippingAddressData;
import com.spring.app.warehouse.payload.WarehouseData;

import java.util.List;

public class OrderData {
    private Long id;
    private String orderCode;
    private Long customerId;
    private String customerCode;
    private String senderName;
    private String senderPhone;
    private String senderAddress;
    private Double subtotal;
    private Double totalWeight;
    private String status;
    private String note;
    private String feedback;
    private Boolean notification;
    private Boolean shipPayer;
    private Double shippingFee;
    private String coupon;
    private WarehouseData warehouse;
    private ShipperData shipper;
    private ShippingAddressData shippingAddress;
    private String shippingType;
    private String shippingTime;
    private String returnCode;
    private String createdAt;
    private String updateAt;

    public OrderData() {}

    public OrderData(
            Long id,
            String orderCode,
            Long customerId,
            String customerCode,
            String status,
            String feedback,
            String note,
            Double subtotal,
            String senderName,
            String senderPhone,
            String senderAddress,
            Boolean notification,
            Double shippingFee,
            String shippingType,
            String shippingTime,
            String coupon,
            WarehouseData warehouse,
            String returnCode,
            List<OrderItemData> orderItem,
            Double totalWeight
    ) {
        this.id = id;
        this.customerId = customerId;
        this.customerCode = customerCode;
        this.orderCode = orderCode;
        this.status = status;
        this.feedback = feedback;
        this.note = note;
        this.subtotal = subtotal;
        this.senderName = senderName;
        this.senderPhone = senderPhone;
        this.senderAddress = senderAddress;
        this.notification = notification;
        this.shippingFee = shippingFee;
        this.shippingType = shippingType;
        this.shippingTime = shippingTime;
        this.coupon = coupon;
        this.warehouse = warehouse;
        this.returnCode = returnCode;
        this.totalWeight = totalWeight;
    }
}
