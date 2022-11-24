package com.spring.app.price.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="price_rule_areas")
public class RuleArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="price_rule_id", referencedColumnName = "id", nullable = false)
    private Rule rule;

    @Column(name = "province_id")
    private String provinceId;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "area_code")
    private String areaCode;

    @Column(name = "center_area_shipping_time")
    private Integer centerAreaShippingTime;

    @Column(name = "outskirts_area_shipping_time")
    private Integer outskirtsAreaShippingTime;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public RuleArea() {}

    public RuleArea(
            Rule rule,
            String provinceId,
            String provinceName,
            String areaCode,
            Integer centerAreaShippingTime,
            Integer outskirtsAreaShippingTime
    ) {
        this.rule = rule;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.areaCode = areaCode;
        this.centerAreaShippingTime = centerAreaShippingTime;
        this.outskirtsAreaShippingTime = outskirtsAreaShippingTime;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
