package com.spring.app.region.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="region_ward")
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ward_id")
    private String wardId;

    @Column(name = "ward_name")
    private String wardName;

    @Column(name = "ward_type")
    private String wardType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="district_id", referencedColumnName = "id")
    private District district;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public Ward() {}

    public Ward(
            String wardId,
            String wardName,
            String wardType,
            District district
    ) {
        this.wardId = wardId;
        this.wardName = wardName;
        this.wardType = wardType;
        this.district = district;
    }

    public String getWardId() {
        return wardId;
    }

    public Ward setWardId(String wardId) {
        this.wardId = wardId;
        return this;
    }

    public String getWardName() {
        return wardName;
    }

    public Ward setWardName(String wardName) {
        this.wardName = wardName;
        return this;
    }

    public String getWardType() {
        return wardType;
    }

    public Ward setWardType(String wardType) {
        this.wardType = wardType;
        return this;
    }

    public District getDistrict() {
        return district;
    }

    public Ward setDistrict(District district) {
        this.district = district;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Long getId() {
        return id;
    }

    public Ward setId(Long id) {
        this.id = id;
        return this;
    }
}
