package com.spring.app.customers.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="for_control")
public class ForControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;

    @Column(name="holder_name")
    private String holderName;

    @Column(name="card_number")
    private String cardNumber;

    @Column(name="bank")
    private String bank;

    @Column(name="address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public ForControl() {}

    public ForControl(
        String holderName,
        String cardNumber,
        String bank,
        String address,
        Customer customer
    ) {
        this.holderName = holderName;
        this.cardNumber = cardNumber;
        this.bank = bank;
        this.address = address;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getHolderName() {
        return holderName;
    }

    public ForControl setHolderName(String holderName) {
        this.holderName = holderName;
        return this;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public ForControl setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public String getBank() {
        return bank;
    }

    public ForControl setBank(String bank) {
        this.bank = bank;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ForControl setAddress(String address) {
        this.address = address;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ForControl setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
