package com.spring.app.admin.config.models.repository;

import com.spring.app.admin.config.models.AdminConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminConfigRepository extends JpaRepository<AdminConfig, Long> {
    Optional<AdminConfig> findFirstByKeyAndScope(String key, String scope);

    List<AdminConfig> findByScope(String scope);

    void deleteAdminConfigByScope(String scope);
}
