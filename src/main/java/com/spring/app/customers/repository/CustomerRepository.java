package com.spring.app.customers.repository;

import com.spring.app.authentication.models.User;
import com.spring.app.customers.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByUser(User user);
}
