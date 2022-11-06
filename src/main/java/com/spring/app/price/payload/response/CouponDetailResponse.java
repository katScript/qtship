package com.spring.app.price.payload.response;

public class CouponDetailResponse {
    private Long id;
    private String code;
    private Boolean enable;
    private String rule;
    private Double value;
    private String validFrom;
    private String validTo;
    private String validRoles;
    private String createdAt;
    private String updateAt;

    public CouponDetailResponse() {}

    public CouponDetailResponse(
            Long id,
            String code,
            Boolean enable,
            String rule,
            Double value,
            String validFrom,
            String validTo,
            String validRoles,
            String createdAt,
            String updateAt
    ) {
        this.id = id;
        this.code = code;
        this.rule = rule;
        this.value = value;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.validRoles = validRoles;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }

    public String getValidRoles() {
        return validRoles;
    }

    public void setValidRoles(String validRoles) {
        this.validRoles = validRoles;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
