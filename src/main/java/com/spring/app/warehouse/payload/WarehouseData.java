package com.spring.app.warehouse.payload;

public class WarehouseData {
    private Long id;
    private String name;
    private String customerCode;
    private String address;
    private String phone;
    private String province;
    private String provinceId;
    private String district;
    private String districtId;
    private String ward;
    private String wardId;
    private String street;
    private String createdAt;
    private String updatedAt;

    public WarehouseData() {}

    public WarehouseData(
            Long id,
            String name,
            String address,
            String customerCode,
            String phone,
            String province,
            String provinceId,
            String district,
            String districtId,
            String ward,
            String wardId,
            String street
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.customerCode = customerCode;
        this.province = province;
        this.provinceId = provinceId;
        this.district = district;
        this.districtId = districtId;
        this.ward = ward;
        this.wardId = wardId;
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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
}
