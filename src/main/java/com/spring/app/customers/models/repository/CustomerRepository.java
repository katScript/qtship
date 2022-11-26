package com.spring.app.customers.models.repository;

import com.spring.app.authentication.models.User;
import com.spring.app.customers.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUser(User user);

    @Query("select c from Customer c where (:id is null or c.id = :id) " +
            "and (:fullName is null or c.fullName = :fullName) " +
            "and (:companyName is null or c.companyName = :companyName) " +
            "and (:phone is null or c.phone = :phone) " +
            "and (:email) is null or c.email = :email")
    Page<Customer> findAllWithFilter(Long id, String fullName, String companyName, String phone, String email, Pageable pageable);
}
