package com.spring.app.region.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="region_province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "province_id")
    private String provinceId;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "province_type")
    private String provinceType;

    @OneToMany(mappedBy = "province",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<District> districts;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public Province() {
        this.districts = new HashSet<>();
    }

    public Province (
            String provinceId,
            String provinceName,
            String provinceType,
            Set<District> districts
    ) {
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.provinceType = provinceType;
        this.districts = districts;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public Province setProvinceId(String provinceId) {
        this.provinceId = provinceId;
        return this;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public Province setProvinceName(String provinceName) {
        this.provinceName = provinceName;
        return this;
    }

    public String getProvinceType() {
        return provinceType;
    }

    public Province setProvinceType(String provinceType) {
        this.provinceType = provinceType;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Set<District> getDistricts() {
        return districts;
    }

    public Province setDistricts(Set<District> districts) {
        this.districts = districts;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Province setId(Long id) {
        this.id = id;
        return this;
    }
}
