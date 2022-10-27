package com.spring.app.products.models.repository;

import com.spring.app.customers.models.Customer;
import com.spring.app.products.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCustomer(Customer customer);
    Product findBySku(String sku);
}
