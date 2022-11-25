package com.spring.app.liquidity.models;

import com.spring.app.customers.models.Customer;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="liquidity")
public class Liquidity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @Column(name = "money_receivable")
    private Double moneyReceivable;

    @Column(name = "pp_pm")
    private Double ppPm;

    @Column(name = "cc_cash")
    private Double ccCash;

    @Column(name = "pp_cash")
    private Double ppCash;

    @Column(name = "for_control")
    private Double forControl;

    @Column(name = "receivable")
    private Double receivable;

    @Column(name = "coupon_cash")
    private Double couponCash;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public Liquidity() {}

    public Liquidity(
            Customer customer,
            Double moneyReceivable,
            Double ppPm,
            Double ccCash,
            Double ppCash,
            Double forControl,
            Double receivable,
            Double couponCash
    ) {
        this.customer = customer;
        this.moneyReceivable = moneyReceivable;
        this.ppPm = ppPm;
        this.ccCash = ccCash;
        this.ppCash = ppCash;
        this.forControl = forControl;
        this.receivable = receivable;
        this.couponCash = couponCash;
    }

    public Long getId() {
        return id;
    }

    public Liquidity setId(Long id) {
        this.id = id;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Liquidity setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Double getMoneyReceivable() {
        return moneyReceivable;
    }

    public Liquidity setMoneyReceivable(Double moneyReceivable) {
        this.moneyReceivable = moneyReceivable;
        return this;
    }

    public Double getPpPm() {
        return ppPm;
    }

    public Liquidity setPpPm(Double ppPm) {
        this.ppPm = ppPm;
        return this;
    }

    public Double getCcCash() {
        return ccCash;
    }

    public Liquidity setCcCash(Double ccCash) {
        this.ccCash = ccCash;
        return this;
    }

    public Double getPpCash() {
        return ppCash;
    }

    public Liquidity setPpCash(Double ppCash) {
        this.ppCash = ppCash;
        return this;
    }

    public Double getForControl() {
        return forControl;
    }

    public Liquidity setForControl(Double forControl) {
        this.forControl = forControl;
        return this;
    }

    public Double getReceivable() {
        return receivable;
    }

    public Liquidity setReceivable(Double receivable) {
        this.receivable = receivable;
        return this;
    }

    public Double getCouponCash() {
        return couponCash;
    }

    public Liquidity setCouponCash(Double couponCash) {
        this.couponCash = couponCash;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
