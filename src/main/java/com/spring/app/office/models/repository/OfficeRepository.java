package com.spring.app.office.models.repository;

import com.spring.app.office.models.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfficeRepository extends JpaRepository<Office, Long> {
    List<Office> findByProvinceId(String provinceId);
}
