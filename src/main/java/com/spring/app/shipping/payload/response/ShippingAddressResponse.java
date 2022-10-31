package com.spring.app.shipping.payload.response;

public class ShippingAddressResponse {
    private String name;
    private String phone;
    private String province;
    private String provinceId;
    private String district;
    private String districtId;
    private String ward;
    private String wardId;
    private String street;

    public ShippingAddressResponse() {}

    public ShippingAddressResponse(
            String name,
            String phone,
            String province,
            String district,
            String ward,
            String provinceId,
            String districtId,
            String wardId,
            String street
    ) {
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.wardId = wardId;
        this.street = street;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }
}
