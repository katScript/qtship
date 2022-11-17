package com.spring.app.products.models.repository;

import com.spring.app.customers.models.Customer;
import com.spring.app.products.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCustomer(Customer customer, Pageable pageable);
    Product findBySku(String sku);
    Page<Product> findAll(Pageable pageable);
}
