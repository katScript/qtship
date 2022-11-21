package com.spring.app.orders.models;

import com.spring.app.customers.models.Customer;
import com.spring.app.products.models.Package;
import com.spring.app.shipping.models.Shipper;
import com.spring.app.shipping.models.ShippingAddress;
import com.spring.app.warehouse.models.Warehouse;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    public static final String PREFIX = "ORD";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @Column(name = "sender_name")
    private String senderName;
    @Column(name = "sender_phone")
    private String senderPhone;
    @Column(name = "sender_address")
    private String senderAddress;
    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "total_weight")
    private Double totalWeight;
    @Column(name = "note")
    private String note;
    @Column(name = "status")
    private String status;
    @Column(name = "feedback")
    private String feedback;
    @Column(name = "notification")
    private Boolean notification;
    @Column(name = "ship_payer")
    private Boolean shipPayer;
    @Column(name = "shipping_fee")
    private Double shippingFee;
    @Column(name = "coupon")
    private String coupon;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shipper_id", referencedColumnName = "id")
    private Shipper shipper;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_id", referencedColumnName = "id")
    private ShippingAddress shippingAddress;
    @Column(name = "shipping_type")
    private String shippingType;
    @Column(name = "taken_time")
    private Date takenTime;
    @Column(name = "shipping_time")
    private Date shippingTime;
    @Column(name = "return_code")
    private String returnCode;
    @OneToMany(mappedBy = "order",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Package> packages;
    @OneToMany(mappedBy = "order",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderLog> histories;
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public Order() {
        this.packages = new HashSet<>();
        this.histories = new HashSet<>();
    }

    public Order (
            Customer customer,
            String senderName,
            String senderPhone,
            String senderAddress,
            String note,
            String status,
            String feedback,
            Boolean notification,
            Boolean shipPayer,
            Double shippingFee,
            String coupon,
            Warehouse warehouse,
            Shipper shipper,
            ShippingAddress shippingAddress,
            String shippingType,
            Date takenTime,
            Date shippingTime,
            String returnCode,
            Set<Package> packages,
            Set<OrderLog> histories
    ) {
        this.customer = customer;
        this.senderName = senderName;
        this.senderPhone = senderPhone;
        this.senderAddress = senderAddress;
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
        this.packages = packages;
        this.takenTime = takenTime;
        this.histories = histories;
    }

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
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

    public Double getSubtotal() {
        return subtotal;
    }

    public Order setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
        return this;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public Order setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
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

    public Boolean getShipPayer() {
        return shipPayer;
    }

    public Order setShipPayer(Boolean shipPayer) {
        this.shipPayer = shipPayer;
        return this;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public Order setShippingFee(Double shippingFee) {
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

    public Shipper getShipper() {
        return shipper;
    }

    public Order setShipper(Shipper shipper) {
        this.shipper = shipper;
        return this;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public Order setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
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

    public Set<Package> getPackages() {
        return packages;
    }

    public Order setPackages(Set<Package> packages) {
        this.packages = packages;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getTakenTime() {
        return takenTime;
    }

    public Order setTakenTime(Date takenTime) {
        this.takenTime = takenTime;
        return this;
    }

    public Set<OrderLog> getHistories() {
        return histories;
    }

    public void setHistories(Set<OrderLog> histories) {
        this.histories = histories;
    }
}
