package com.spring.app.warehouse.models;

import com.spring.app.customers.models.Customer;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    public Warehouse() {}

    public Warehouse(Customer customer) {
        this.customer = customer;
    }

    public Warehouse(
            String name,
            String address,
            String phone,
            Customer customer,
            String province,
            String provinceId,
            String district,
            String districtId,
            String ward,
            String wardId,
            String street
    ) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Warehouse setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Warehouse setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Warehouse setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Warehouse setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public Warehouse setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public Warehouse setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getWard() {
        return ward;
    }

    public Warehouse setWard(String ward) {
        this.ward = ward;
        return this;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public Warehouse setProvinceId(String provinceId) {
        this.provinceId = provinceId;
        return this;
    }

    public String getDistrictId() {
        return districtId;
    }

    public Warehouse setDistrictId(String districtId) {
        this.districtId = districtId;
        return this;
    }

    public String getWardId() {
        return wardId;
    }

    public Warehouse setWardId(String wardId) {
        this.wardId = wardId;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Warehouse setStreet(String street) {
        this.street = street;
        return this;
    }
}
