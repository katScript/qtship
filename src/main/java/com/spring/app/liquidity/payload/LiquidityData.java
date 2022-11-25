package com.spring.app.liquidity.payload;

public class LiquidityData {
    private Long id;
    private Long customerId;
    private Double moneyReceivable;
    private Double ppPm;
    private Double ccCash;
    private Double ppCash;
    private Double forControl;
    private Double receivable;
    private Double couponCash;
    private String createdAt;
    private String updatedAt;

    public LiquidityData() {}

    public LiquidityData(
            Long id,
            Long customerId,
            Double moneyReceivable,
            Double ppPm,
            Double ccCash,
            Double ppCash,
            Double forControl,
            Double receivable,
            Double couponCash,
            String createdAt,
            String updatedAt
    ) {
        this.id = id;
        this.customerId = customerId;
        this.moneyReceivable = moneyReceivable;
        this.ppPm = ppPm;
        this.ccCash = ccCash;
        this.ppCash = ppCash;
        this.forControl = forControl;
        this.receivable = receivable;
        this.couponCash = couponCash;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getMoneyReceivable() {
        return moneyReceivable;
    }

    public void setMoneyReceivable(Double moneyReceivable) {
        this.moneyReceivable = moneyReceivable;
    }

    public Double getPpPm() {
        return ppPm;
    }

    public void setPpPm(Double ppPm) {
        this.ppPm = ppPm;
    }

    public Double getCcCash() {
        return ccCash;
    }

    public void setCcCash(Double ccCash) {
        this.ccCash = ccCash;
    }

    public Double getPpCash() {
        return ppCash;
    }

    public void setPpCash(Double ppCash) {
        this.ppCash = ppCash;
    }

    public Double getForControl() {
        return forControl;
    }

    public void setForControl(Double forControl) {
        this.forControl = forControl;
    }

    public Double getReceivable() {
        return receivable;
    }

    public void setReceivable(Double receivable) {
        this.receivable = receivable;
    }

    public Double getCouponCash() {
        return couponCash;
    }

    public void setCouponCash(Double couponCash) {
        this.couponCash = couponCash;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
