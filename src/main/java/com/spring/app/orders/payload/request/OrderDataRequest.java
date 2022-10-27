package com.spring.app.orders.payload.request;

import com.spring.app.warehouse.payload.request.WarehouseDataRequest;

import java.util.List;

public class OrderDataRequest {
    private Long id;
    private Long customerId;
    private String senderName;
    private String senderPhone;
    private String senderAddress;
    private String note;
    private String status;
    private String feedback;
    private Boolean notification;
    private Boolean shippingFee;
    private String coupon;
    private Long warehouseId;
    private String shippingType;
    private List<OrderItemRequest> orderItem;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Boolean getNotification() {
        return notification;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public Boolean getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Boolean shippingFee) {
        this.shippingFee = shippingFee;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public List<OrderItemRequest> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItemRequest> orderItem) {
        this.orderItem = orderItem;
    }

}
