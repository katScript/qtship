package com.spring.app.customers.payload;

import java.util.Date;

public class AddressData {
    private Long id;
    private String province;
    private String district;
    private String ward;
    private String provinceId;
    private String districtId;
    private String wardId;
    private String street;
    private boolean primary;
    private Date createdAt;
    private Date updatedAt;

    public AddressData() {}

    public AddressData(com.spring.app.customers.models.Address address) {
        this.id = address.getId();
        this.province = address.getProvince();
        this.provinceId = address.getProvinceId();
        this.district = address.getDistrict();
        this.districtId = address.getDistrictId();
        this.ward = address.getWard();
        this.wardId = address.getWardId();
        this.street = address.getStreet();
        this.primary = address.getPrimary();
        this.createdAt = address.getCreatedAt();
        this.updatedAt = address.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public boolean getPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
