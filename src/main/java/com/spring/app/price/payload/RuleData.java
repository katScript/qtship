package com.spring.app.price.payload;

import java.util.List;
import java.util.Set;

public class RuleData {
    private Long id;
    private String name;
    private String description;
    private List<RuleAreaData> area;
    private List<RulePriceListData> priceLists;
    private String createdAt;
    private String updatedAt;

    public RuleData() {}

    public RuleData(
            Long id,
            String name,
            String description,
            List<RuleAreaData> area,
            List<RulePriceListData> priceLists,
            String createdAt,
            String updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.area = area;
        this.priceLists = priceLists;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RuleAreaData> getArea() {
        return area;
    }

    public void setArea(List<RuleAreaData> area) {
        this.area = area;
    }

    public List<RulePriceListData> getPriceLists() {
        return priceLists;
    }

    public void setPriceLists(List<RulePriceListData> priceLists) {
        this.priceLists = priceLists;
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
