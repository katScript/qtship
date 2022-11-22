package com.spring.app.patch.models.repository;

import com.spring.app.patch.models.Patch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatchRepository  extends JpaRepository<Patch, Long> {
    Optional<Patch> findByPatch(String path);
    Boolean existsByPatch(String path);
}
