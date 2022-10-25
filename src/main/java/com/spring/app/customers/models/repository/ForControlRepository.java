package com.spring.app.customers.models.repository;

import com.spring.app.customers.models.ForControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForControlRepository extends JpaRepository<ForControl, Long> {
}
