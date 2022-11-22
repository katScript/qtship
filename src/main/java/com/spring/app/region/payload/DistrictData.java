package com.spring.app.region.payload;

public class DistrictData {
    private String district_id;
    private String district_name;
    private String district_type;
    private Double lat;
    private Double lng;
    private String province_id;

    public DistrictData() {}

    public DistrictData(
            String district_id,
            String district_name,
            String district_type,
            Double lat,
            Double lng,
            String province_id
    ) {
        this.district_id = district_id;
        this.district_name = district_name;
        this.district_type = district_type;
        this.lat = lat;
        this.lng = lng;
        this.province_id = province_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getDistrict_type() {
        return district_type;
    }

    public void setDistrict_type(String district_type) {
        this.district_type = district_type;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getProvince_id() {
        return province_id;
    }

    public void setProvince_id(String province_id) {
        this.province_id = province_id;
    }
}
