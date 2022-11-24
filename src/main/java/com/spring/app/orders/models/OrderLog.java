package com.spring.app.orders.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="orders_log")
public class OrderLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="order_id", referencedColumnName = "id", nullable = false)
    private Order order;
    @Column(name = "log_data")
    private String data;
    @Column(name = "status")
    private String status;
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public OrderLog() {}

    public OrderLog(
            Order order,
            String data,
            String status
    ) {
        this.order = order;
        this.data = data;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public OrderLog setId(Long id) {
        this.id = id;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public OrderLog setOrder(Order order) {
        this.order = order;
        return this;
    }

    public String getData() {
        return data;
    }

    public OrderLog setData(String data) {
        this.data = data;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public OrderLog setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public OrderLog setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public OrderLog setStatus(String status) {
        this.status = status;
        return this;
    }
}
