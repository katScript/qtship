package com.spring.app.orders.models;

import com.spring.app.customers.models.Customer;
import com.spring.app.shipping.models.Shipper;
import com.spring.app.warehouse.models.Warehouse;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order {
    public static final String PREFIX = "ORD";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "note")
    private String note;
    @Column(name = "feedback")
    private String feedback;
    @Column(name = "notification")
    private Boolean notification;
    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "total_weight")
    private Double totalWeight;
    @Column(name = "sender_name")
    private String senderName;
    @Column(name = "sender_phone")
    private String senderPhone;
    @Column(name = "sender_address")
    private String senderAddress;
    @Column(name = "shipping_fee")
    private Boolean shippingFee;
    @Column(name = "shipping_type")
    private String shippingType;
    @Column(name = "shipping_time")
    private Date shippingTime;
    @Column(name = "coupon")
    private String coupon;
    @Column(name = "return_code")
    private String returnCode;
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;
    private String status;
    @OneToMany(mappedBy = "order",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItemSet = new HashSet<>();
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="customer_id", referencedColumnName = "id")
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="shipper_id", referencedColumnName = "id")
    private Shipper shipper;

    public Order() {}

    public Order(
            String senderName,
            String senderPhone,
            String senderAddress,
            String note,
            String feedback,
            Boolean notification,
            Boolean shippingFee,
            String coupon,
            String shippingType,
            Date shippingTime,
            String returnCode
    ) {
        this.senderName = senderName;
        this.senderPhone = senderPhone;
        this.senderAddress = senderAddress;
        this.note = note;
        this.feedback = feedback;
        this.notification = notification;
        this.shippingFee = shippingFee;
        this.coupon = coupon;
        this.shippingType = shippingType;
        this.shippingTime = shippingTime;
        this.returnCode = returnCode;
    }

    public Long getId() {
        return id;
    }

    public Set<OrderItem> getOrderItemSet() {
        return orderItemSet;
    }

    public Order setOrderItemSet(Set<OrderItem> orderItemSet) {
        this.orderItemSet = orderItemSet;
        return this;
    }

    public Order addOrderItem(OrderItem orderItem) {
        this.orderItemSet.add(orderItem);
        return this;
    }

    public String getOrderCode() {
        return PREFIX + String.format("%06d", id);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Order setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }
    public String getSenderName() {
        return senderName;
    }

    public Order setSenderName(String senderName) {
        this.senderName = senderName;
        return this;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public Order setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
        return this;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public Order setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
        return this;
    }


    public String getNote() {
        return note;
    }

    public Order setNote(String note) {
        this.note = note;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Order setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getFeedback() {
        return feedback;
    }

    public Order setFeedback(String feedback) {
        this.feedback = feedback;
        return this;
    }

    public Boolean getNotification() {
        return notification;
    }

    public Order setNotification(Boolean notification) {
        this.notification = notification;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Order setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
        return this;
    }

    public Boolean getShippingFee() {
        return shippingFee;
    }

    public Order setShippingFee(Boolean shippingFee) {
        this.shippingFee = shippingFee;
        return this;
    }

    public String getCoupon() {
        return coupon;
    }

    public Order setCoupon(String coupon) {
        this.coupon = coupon;
        return this;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Order setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
        return this;
    }

    public String getShippingType() {
        return shippingType;
    }

    public Order setShippingType(String shippingType) {
        this.shippingType = shippingType;
        return this;
    }

    public Date getShippingTime() {
        return shippingTime;
    }

    public Order setShippingTime(Date shippingTime) {
        this.shippingTime = shippingTime;
        return this;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public Order setReturnCode(String returnCode) {
        this.returnCode = returnCode;
        return this;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }
}
