package com.spring.app.region.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="region_district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "district_id")
    private String districtId;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "district_type")
    private String districtType;

    @Column(name = "lat")
    private Double latitude;

    @Column(name = "lng")
    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="province_id", referencedColumnName = "id")
    private Province province;

    @OneToMany(mappedBy = "district",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ward> wards;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public District() {
        this.wards = new HashSet<>();
    }

    public District(
            String districtId,
            String districtName,
            String districtType,
            Double latitude,
            Double longitude,
            Province province,
            Set<Ward> wards
    ) {
        this.districtId = districtId;
        this.districtName = districtName;
        this.districtType = districtType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.province = province;
        this.wards = wards;
    }

    public String getDistrictId() {
        return districtId;
    }

    public District setDistrictId(String districtId) {
        this.districtId = districtId;
        return this;
    }

    public String getDistrictName() {
        return districtName;
    }

    public District setDistrictName(String districtName) {
        this.districtName = districtName;
        return this;
    }

    public String getDistrictType() {
        return districtType;
    }

    public District setDistrictType(String districtType) {
        this.districtType = districtType;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public District setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public District setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Province getProvince() {
        return province;
    }

    public District setProvince(Province province) {
        this.province = province;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Set<Ward> getWards() {
        return wards;
    }

    public District setWards(Set<Ward> wards) {
        this.wards = wards;
        return this;
    }

    public Long getId() {
        return id;
    }

    public District setId(Long id) {
        this.id = id;
        return this;
    }
}
