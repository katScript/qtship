package com.spring.app.orders.models;

import com.spring.app.customers.models.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order {
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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status", referencedColumnName = "code")
    private OrderStatus status;
    @OneToMany(mappedBy = "order",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderItem> orderItemSet = new HashSet<>();
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public Order() {}

    public Order(
            String note,
            String feedback,
            Boolean notification
    ) {
        this.note = note;
        this.feedback = feedback;
        this.notification = notification;
    }

    public Order(
            String note,
            OrderStatus status,
            String feedback,
            Boolean notification,
            Customer customer
    ) {
        this.note = note;
        this.feedback = feedback;
        this.notification = notification;
        this.status = status;
        this.customer = customer;
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
        return String.format("%09d", id);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Order setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Order setNote(String note) {
        this.note = note;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Order setStatus(OrderStatus status) {
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
}
