package com.spring.app.orders.payload;

import com.spring.app.warehouse.payload.WarehouseData;

import java.util.List;

public class OrderData {
    private Long id;
    private String orderCode;
    private Long customerId;
    private String customerCode;
    private String status;
    private String feedback;
    private String note;
    private Double subtotal;
    private Double totalWeight;
    private String senderName;
    private String senderPhone;
    private String senderAddress;
    private Boolean notification;
    private Boolean shippingFee;
    private String shippingType;
    private String shippingTime;
    private String coupon;
    private WarehouseData warehouse;
    private String returnCode;
    private List<OrderItemData> orderItem;
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
        Boolean shippingFee,
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
        this.orderItem = orderItem;
        this.totalWeight = totalWeight;
    }

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

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
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

    public WarehouseData getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseData warehouse) {
        this.warehouse = warehouse;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public List<OrderItemData> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItemData> orderItem) {
        this.orderItem = orderItem;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }
}
