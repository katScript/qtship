package com.spring.app.region.models.repository;

import com.spring.app.region.models.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
    @Query("select w from Ward w where w.district.districtId = :districtId")
    List<Ward> findByDistrictId(String districtId);
}
