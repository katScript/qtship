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
    @Column(name = "enable")
    private Boolean enable;
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
    public static String BASE = "base";
    public static String PERCENT = "percent";

    public Coupon() {}

    public Coupon(
            String code,
            String rule,
            Double value,
            Date validFrom,
            Date validTo,
            String validRoles,
            Boolean enable
    ) {
        this.code = code;
        this.rule = rule;
        this.value = value;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.validRoles = validRoles;
        this.enable = enable;
    }

    public Boolean isValid() {
        return this.getValidFrom().after(new Date()) && this.getValidTo().after(new Date());
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Coupon setCode(String code) {
        this.code = code;
        return this;
    }

    public String getRule() {
        return rule;
    }

    public Coupon setRule(String rule) {
        this.rule = rule;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public Coupon setValue(Double value) {
        this.value = value;
        return this;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public Coupon setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    public Date getValidTo() {
        return validTo;
    }

    public Coupon setValidTo(Date validTo) {
        this.validTo = validTo;
        return this;
    }

    public String getValidRoles() {
        return validRoles;
    }

    public Coupon setValidRoles(String validRoles) {
        this.validRoles = validRoles;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Coupon setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Coupon setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
