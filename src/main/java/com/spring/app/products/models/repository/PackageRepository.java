package com.spring.app.products.models.repository;

import com.spring.app.orders.models.Order;
import com.spring.app.products.models.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    List<Package> findByOrder(Order order);
}
