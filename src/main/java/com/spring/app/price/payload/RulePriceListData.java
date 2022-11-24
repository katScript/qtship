package com.spring.app.price.payload;

import java.util.List;

public class RulePriceListData {
    private Long id;
    private Long ruleId;
    private String areaCode;
    private List<Double> priceList;
    private Double advancePrice;
    private Double weightFactor;
    private Double advanceWeightFactor;
    private String createdAt;
    private String updatedAt;

    public RulePriceListData() {}

    public RulePriceListData(
            Long id,
            Long ruleId,
            String areaCode,
            List<Double> priceList,
            Double advancePrice,
            Double weightFactor,
            Double advanceWeightFactor,
            String createdAt,
            String updatedAt
    ) {
        this.id = id;
        this.ruleId = ruleId;
        this.areaCode = areaCode;
        this.priceList = priceList;
        this.advancePrice = advancePrice;
        this.weightFactor = weightFactor;
        this.advanceWeightFactor = advanceWeightFactor;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public List<Double> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Double> priceList) {
        this.priceList = priceList;
    }

    public Double getAdvancePrice() {
        return advancePrice;
    }

    public void setAdvancePrice(Double advancePrice) {
        this.advancePrice = advancePrice;
    }

    public Double getWeightFactor() {
        return weightFactor;
    }

    public void setWeightFactor(Double weightFactor) {
        this.weightFactor = weightFactor;
    }

    public Double getAdvanceWeightFactor() {
        return advanceWeightFactor;
    }

    public void setAdvanceWeightFactor(Double advanceWeightFactor) {
        this.advanceWeightFactor = advanceWeightFactor;
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
