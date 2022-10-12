package com.spring.app.customers.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="customer_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="province")
    private String province;

    @Column(name="district")
    private String district;

    @Column(name="ward")
    private String ward;

    @Column(name="province_id")
    private Integer provinceId;

    @Column(name="district_id")
    private Integer districtId;

    @Column(name="ward_id")
    private Integer wardId;

    @Column(name="street")
    private String street;

    @Column(name="primary")
    private Boolean primary;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public Address() {}

    public Address(
        String province,
        Integer provinceId,
        String district,
        Integer districtId,
        String ward,
        Integer wardId,
        String street,
        boolean primary
    ) {
        this.province = province;
        this.provinceId = provinceId;
        this.district = district;
        this.districtId = districtId;
        this.ward = ward;
        this.wardId = wardId;
        this.street = street;
        this.primary = primary;
    }

    public Long getId() {
        return id;
    }

    public String getProvince() {
        return province;
    }

    public Address setProvince(String province) {
        this.province = province;
        return this;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public Address setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public Address setDistrict(String district) {
        this.district = district;
        return this;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public Address setDistrictId(Integer districtId) {
        this.districtId = districtId;
        return this;
    }

    public String getWard() {
        return ward;
    }

    public Address setWard(String ward) {
        this.ward = ward;
        return this;
    }

    public Integer getWardId() {
        return wardId;
    }

    public Address setWardId(Integer wardId) {
        this.wardId = wardId;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public Address setPrimary(Boolean primary) {
        this.primary = primary;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Address setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
