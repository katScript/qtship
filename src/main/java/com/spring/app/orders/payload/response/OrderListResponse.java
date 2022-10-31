package com.spring.app.orders.payload.response;

import java.time.LocalDateTime;
import java.util.List;

public class OrderListResponse {
    private String id;
    private String customerId;
    private String status;
    private String feedback;
    private String node;
    private Double subtotal;
    private String senderName;
    private String senderPhone;
    private String senderAddress;
    private Boolean notification;
    private Boolean shippingFee;
    private String shippingType;
    private String shippingTime;
    private String coupon;
    private List<OrderItemResponse> orderItemResponse;

    public OrderListResponse() {}

    public OrderListResponse(
            String id,
            String customerId,
            String status,
            String feedback,
            String node,
            Double subtotal,
            String senderName,
            String senderPhone,
            String senderAddress,
            Boolean notification,
            Boolean shippingFee,
            String shippingType,
            String shippingTime,
            String coupon,
            List<OrderItemResponse> orderItemResponse
    ) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.feedback = feedback;
        this.node = node;
        this.subtotal = subtotal;
        this.senderName = senderName;
        this.senderPhone = senderPhone;
        this.senderAddress = senderAddress;
        this.notification = notification;
        this.shippingFee = shippingFee;
        this.shippingType = shippingType;
        this.shippingTime = shippingTime;
        this.coupon = coupon;
        this.orderItemResponse = orderItemResponse;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
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

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public String getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(String shippingTime) {
        this.shippingTime = shippingTime;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OrderItemResponse> getOrderItemResponse() {
        return orderItemResponse;
    }

    public void setOrderItemResponse(List<OrderItemResponse> orderItemResponse) {
        this.orderItemResponse = orderItemResponse;
    }
}
