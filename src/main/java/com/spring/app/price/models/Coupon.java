package com.spring.app.price.models;

import com.spring.app.customers.models.Customer;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="coupons")
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "code")
    private String code;
    @Column(name = "rule")
    private String rule;
    @Column(name = "value")
    private Double value;
    @Column(name = "valid_from")
    private Date validFrom;
    @Column(name = "valid_to")
    private Date validTo;
    @Column(name = "valid_roles")
    private String validRoles;
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public Coupon() {}

    public Coupon(
            String code,
            String rule,
            Double value,
            Date validFrom,
            Date validTo,
            String validRoles
    ) {
        this.code = code;
        this.rule = rule;
        this.value = value;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.validRoles = validRoles;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public String getValidRoles() {
        return validRoles;
    }

    public void setValidRoles(String validRoles) {
        this.validRoles = validRoles;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
