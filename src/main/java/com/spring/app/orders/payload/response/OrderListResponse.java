package com.spring.app.orders.payload.response;

import com.spring.app.orders.payload.OrderItem;

import java.util.ArrayList;
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
    private List<OrderItem> orderItem;

    public OrderListResponse() {}

    public OrderListResponse(
            String id,
            String status,
            String customerId,
            String node,
            String feedback,
            Double subtotal,
            String senderName,
            String senderPhone,
            String senderAddress
    ) {
        this.id = id;
        this.status = status;
        this.customerId = customerId;
        this.node = node;
        this.feedback = feedback;
        this.subtotal = subtotal;
        this.senderName = senderName;
        this.senderPhone = senderPhone;
        this.senderAddress = senderAddress;
    }

    public OrderListResponse(
            String id,
            String customerId,
            String node,
            String feedback,
            Double subtotal,
            String senderName,
            String senderPhone,
            String senderAddress,
            List<OrderItem> orderItem
    ) {
        this.id = id;
        this.customerId = customerId;
        this.node = node;
        this.feedback = feedback;
        this.subtotal = subtotal;
        this.orderItem = orderItem;
        this.senderName = senderName;
        this.senderPhone = senderPhone;
        this.senderAddress = senderAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
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

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public void addOrderItem(OrderItem orderItem) {
        if (this.orderItem == null) {
            this.orderItem = new ArrayList<>();
        }

        this.orderItem.add(orderItem);
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
}
