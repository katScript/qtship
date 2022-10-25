package com.spring.app.customers.payload;

import java.util.Date;

public class ForControl {
    private Long id;
    private String holderName;
    private String cardNumber;
    private String bank;
    private String address;
    private Date createdAt;
    private Date updatedAt;

    public ForControl() {}

    public ForControl(com.spring.app.customers.models.ForControl forControl) {
        this.id = forControl.getId();
        this.holderName = forControl.getHolderName();
        this.cardNumber = forControl.getCardNumber();
        this.bank = forControl.getBank();
        this.address = forControl.getAddress();
        this.createdAt = forControl.getCreatedAt();
        this.updatedAt = forControl.getUpdatedAt();
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
