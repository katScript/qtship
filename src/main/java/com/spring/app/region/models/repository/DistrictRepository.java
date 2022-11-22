package com.spring.app.region.models.repository;

import com.spring.app.region.models.District;
import com.spring.app.region.models.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, String> {
    @Query("select d from District d where d.province.provinceId = :provinceId")
    List<District> findByProvinceId(String provinceId);
}
