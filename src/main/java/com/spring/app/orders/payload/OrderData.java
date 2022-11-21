package com.spring.app.orders.payload;

import com.spring.app.products.payload.PackageData;
import com.spring.app.shipping.payload.ShipperData;
import com.spring.app.shipping.payload.ShippingAddressData;
import com.spring.app.warehouse.payload.WarehouseData;

import java.util.HashSet;
import java.util.Set;

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
    private String note;
    private String status;
    private String feedback;
    private Boolean notification;
    private Boolean shipPayer;
    private Double shippingFee;
    private String coupon;
    private WarehouseData warehouse;
    private ShipperData shipper;
    private ShippingAddressData shippingAddress;
    private String shippingType;
    private String takenTime;
    private String shippingTime;
    private String returnCode;
    private Set<PackageData> products;
    private String createdAt;
    private String updatedAt;

    public OrderData() {
        products = new HashSet<>();
    }

    public OrderData(
            Long id,
            String orderCode,
            Long customerId,
            String customerCode,
            String senderName,
            String senderPhone,
            String senderAddress,
            Double subtotal,
            Double totalWeight,
            String note,
            String status,
            String feedback,
            Boolean notification,
            Boolean shipPayer,
            Double shippingFee,
            String coupon,
            WarehouseData warehouse,
            ShipperData shipper,
            ShippingAddressData shippingAddress,
            String shippingType,
            String takenTime,
            String shippingTime,
            String returnCode,
            Set<PackageData> products
    ) {
        this.id = id;
        this.orderCode = orderCode;
        this.customerId = customerId;
        this.customerCode = customerCode;
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.senderPhone = senderPhone;
        this.subtotal = subtotal;
        this.totalWeight = totalWeight;
        this.note = note;
        this.status = status;
        this.feedback = feedback;
        this.notification = notification;
        this.shipPayer = shipPayer;
        this.shippingFee = shippingFee;
        this.coupon = coupon;
        this.warehouse = warehouse;
        this.shipper = shipper;
        this.shippingAddress = shippingAddress;
        this.shippingType = shippingType;
        this.shippingTime = shippingTime;
        this.returnCode = returnCode;
        this.products = products;
        this.takenTime = takenTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
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

    public Boolean getShipPayer() {
        return shipPayer;
    }

    public void setShipPayer(Boolean shipPayer) {
        this.shipPayer = shipPayer;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
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

    public ShipperData getShipper() {
        return shipper;
    }

    public void setShipper(ShipperData shipper) {
        this.shipper = shipper;
    }

    public ShippingAddressData getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddressData shippingAddress) {
        this.shippingAddress = shippingAddress;
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

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public Set<PackageData> getProducts() {
        return products;
    }

    public void setProducts(Set<PackageData> products) {
        this.products = products;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTakenTime() {
        return takenTime;
    }
}
