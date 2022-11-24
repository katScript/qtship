package com.spring.app.price.models;

import com.spring.app.price.converter.PriceListConverter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="price_rule_price_list")
public class RulePriceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="price_rule_id", referencedColumnName = "id", nullable = false)
    private Rule rule;

    @Column(name = "area_code")
    private String areaCode;

    @Column(name = "price_list")
    @Convert(converter = PriceListConverter.class)
    private List<Double> priceList;

    @Column(name = "advance_price")
    private Double advancePrice;

    @Column(name = "weight_factor")
    private Double weightFactor;

    @Column(name = "advance_weight_factor")
    private Double advanceWeightFactor;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public RulePriceList() {}

    public RulePriceList(
            Rule rule,
            String areaCode,
            List<Double> priceList,
            Double advancePrice,
            Double weightFactor,
            Double advanceWeightFactor
    ) {
        this.rule = rule;
        this.areaCode = areaCode;
        this.priceList = priceList;
        this.advancePrice = advancePrice;
        this.weightFactor = weightFactor;
        this.advanceWeightFactor = advanceWeightFactor;
    }

    public Long getId() {
        return id;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
