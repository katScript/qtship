package com.spring.app.orders.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="orders_status")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "label")
    private String label;
    @Column(name = "enable")
    private Boolean enable;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders_status_flow",
                    joinColumns = {@JoinColumn(name = "parent_id")},
                    inverseJoinColumns={@JoinColumn(name="child_id")})
    private Set<OrderStatus> child = new HashSet<>();
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public OrderStatus() {}

    public OrderStatus(
            String code,
            String label,
            Boolean enable
    ) {
        this.code = code;
        this.label = label;
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public OrderStatus setCode(String code) {
        this.code = code;
        return this;
    }

    public Set<OrderStatus> getChild() {
        return child;
    }

    public OrderStatus setChild(Set<OrderStatus> child) {
        this.child = child;
        return this;
    }

    public OrderStatus addChild(OrderStatus child) {
        this.child.add(child);
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
