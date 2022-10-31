package com.spring.app.products.models;

import com.spring.app.orders.models.OrderItem;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="package")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "qty")
    private Integer qty;
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="order_items_id", referencedColumnName = "id", nullable = false)
    private OrderItem orderItem;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public Package () {}

    public Package (
        Integer qty,
        OrderItem orderItem,
        Product product
    ) {
        this.qty = qty;
        this.orderItem = orderItem;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public Integer getQty() {
        return qty;
    }

    public Package setQty(Integer qty) {
        this.qty = qty;
        return this;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public Package setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public Package setProduct(Product product) {
        this.product = product;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
