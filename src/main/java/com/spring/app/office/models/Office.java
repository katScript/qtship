package com.spring.app.office.models;

import com.spring.app.price.models.Rule;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="office")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "province")
    private String province;
    
    @Column(name = "province_id")
    private String provinceId;
    
    @Column(name = "district")
    private String district;

    @Column(name = "district_id")
    private String districtId;

    @Column(name = "ward")
    private String ward;

    @Column(name = "ward_id")
    private String wardId;

    @Column(name = "street")
    private String street;
    
    @Column(name = "phone")
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="price_rule_id", referencedColumnName = "id", nullable = false)
    private Rule priceRule;
    
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public Office() {}

    public Office(
            String name,
            String province,
            String provinceId,
            String district,
            String districtId,
            String ward,
            String wardId,
            String street,
            String phone,
            Rule priceRule
    ) {
        this.name = name;
        this.province = province;
        this.provinceId = provinceId;
        this.district = district;
        this.districtId = districtId;
        this.ward = ward;
        this.wardId = wardId;
        this.street = street;
        this.phone = phone;
        this.priceRule = priceRule;
    }

    public Long getId() {
        return id;
    }

    public Office setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Office setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Office setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getProvince() {
        return province;
    }

    public Office setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public Office setProvinceId(String provinceId) {
        this.provinceId = provinceId;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public Office setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getDistrictId() {
        return districtId;
    }

    public Office setDistrictId(String districtId) {
        this.districtId = districtId;
        return this;
    }

    public String getWard() {
        return ward;
    }

    public Office setWard(String ward) {
        this.ward = ward;
        return this;
    }

    public String getWardId() {
        return wardId;
    }

    public Office setWardId(String wardId) {
        this.wardId = wardId;
        return this;
    }

    public Rule getPriceRule() {
        return priceRule;
    }

    public Office setPriceRule(Rule priceRule) {
        this.priceRule = priceRule;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Office setStreet(String street) {
        this.street = street;
        return this;
    }
}
