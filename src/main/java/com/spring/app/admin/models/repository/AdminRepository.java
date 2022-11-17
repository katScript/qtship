package com.spring.app.admin.models.repository;

import com.spring.app.admin.models.Admin;
import com.spring.app.authentication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUser(User user);
}