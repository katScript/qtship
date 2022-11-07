package com.spring.app.customers.payload;

import java.util.Date;

public class ForControlData {
    private Long id;
    private String holderName;
    private String cardNumber;
    private String bank;
    private String address;
    private String createdAt;
    private String updatedAt;

    public ForControlData() {}

    public ForControlData(
            Long id,
            String holderName,
            String cardNumber,
            String bank,
            String address
    ) {
        this.id = id;
        this.holderName = holderName;
        this.cardNumber = cardNumber;
        this.bank = bank;
        this.address = address;
    }

    public Long getId() {
        return id;
    }
    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
