package com.spring.app.shipping.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="shipping_address")
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name="province")
    private String province;
    @Column(name="district")
    private String district;
    @Column(name="ward")
    private String ward;
    @Column(name="province_id")
    private String provinceId;
    @Column(name="district_id")
    private String districtId;
    @Column(name="ward_id")
    private String wardId;
    @Column(name="street")
    private String street;
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public ShippingAddress() {}

    public ShippingAddress(
            String name,
            String phone,
            String province,
            String provinceId,
            String district,
            String districtId,
            String ward,
            String wardId,
            String street
    ) {
        this.province = province;
        this.provinceId = provinceId;
        this.district = district;
        this.districtId = districtId;
        this.ward = ward;
        this.wardId = wardId;
        this.street = street;
        this.name = name;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ShippingAddress setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ShippingAddress setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public ShippingAddress setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public ShippingAddress setProvinceId(String provinceId) {
        this.provinceId = provinceId;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public ShippingAddress setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getDistrictId() {
        return districtId;
    }

    public ShippingAddress setDistrictId(String districtId) {
        this.districtId = districtId;
        return this;
    }

    public String getWard() {
        return ward;
    }

    public ShippingAddress setWard(String ward) {
        this.ward = ward;
        return this;
    }

    public String getWardId() {
        return wardId;
    }

    public ShippingAddress setWardId(String wardId) {
        this.wardId = wardId;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public ShippingAddress setStreet(String street) {
        this.street = street;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

}
