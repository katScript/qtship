package com.spring.app.customers.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="for_control")
public class ForControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="holder_name")
    private String holderName;

    @Column(name="card_number")
    private String cardNumber;

    @Column(name="bank")
    private String bank;

    @Column(name="address")
    private String address;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public ForControl() {}

    public ForControl(
        Long customerId,
        String holderName,
        String cardNumber,
        String bank,
        String address
    ) {
        this.customerId = customerId;
        this.holderName = holderName;
        this.cardNumber = cardNumber;
        this.bank = bank;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public ForControl setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
