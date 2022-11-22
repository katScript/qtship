package com.spring.app.customers.models.repository;

import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.ForControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForControlRepository extends JpaRepository<ForControl, Long> {
    @Query("select fc from ForControl fc where fc.id = :id and fc.customer = :customer")
    Optional<ForControl> findByIdAndCustomer(Long id, Customer customer);
}
