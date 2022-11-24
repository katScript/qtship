package com.spring.app.price.payload;

public class RuleAreaData {
    private Long id;
    private Long ruleId;
    private String provinceId;
    private String provinceName;
    private String areaCode;
    private Integer centerAreaShippingTime;
    private Integer outskirtsAreaShippingTime;
    private String createdAt;
    private String updatedAt;

    public RuleAreaData() {}

    public RuleAreaData(
            Long id,
            Long ruleId,
            String provinceId,
            String provinceName,
            String areaCode,
            Integer centerAreaShippingTime,
            Integer outskirtsAreaShippingTime,
            String createdAt,
            String updatedAt
    ) {
        this.id = id;
        this.ruleId = ruleId;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.areaCode = areaCode;
        this.centerAreaShippingTime = centerAreaShippingTime;
        this.outskirtsAreaShippingTime = outskirtsAreaShippingTime;
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

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getCenterAreaShippingTime() {
        return centerAreaShippingTime;
    }

    public void setCenterAreaShippingTime(Integer centerAreaShippingTime) {
        this.centerAreaShippingTime = centerAreaShippingTime;
    }

    public Integer getOutskirtsAreaShippingTime() {
        return outskirtsAreaShippingTime;
    }

    public void setOutskirtsAreaShippingTime(Integer outskirtsAreaShippingTime) {
        this.outskirtsAreaShippingTime = outskirtsAreaShippingTime;
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
