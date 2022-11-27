package com.spring.app.office.models.repository;

import com.spring.app.office.models.Office;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OfficeRepository extends JpaRepository<Office, Long> {
    List<Office> findByProvinceId(String provinceId);

    @Query("select o from Office o where (:name is null or o.name like concat('%', :name, '%')) " +
            "and (:phone is null or o.phone like concat('%', :phone, '%')) " +
            "and (:provinceId) is null or o.provinceId = :provinceId")
    Page<Office> findAllWithFilter(String name, String phone, String provinceId, Pageable pageable);

    Optional<Office> findFirstByProvinceId(String provinceId);
}
