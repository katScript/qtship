package com.spring.app.admin.user.models.repository;

import com.spring.app.admin.user.models.Admin;
import com.spring.app.authentication.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUser(User user);

    @Query("select a from Admin a")
    Page<Admin> findAll(Pageable pageable);
}