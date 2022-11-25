package com.spring.app.liquidity.models.repository;

import com.spring.app.customers.models.Customer;
import com.spring.app.liquidity.models.Liquidity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LiquidityRepository extends JpaRepository<Liquidity, Long> {
    @Query("select l from Liquidity l where l.customer = :customer order by l.createdAt desc")
    List<Liquidity> findByCustomer(Customer customer);

    Optional<Liquidity> findTopByCustomerOrderByCreatedAtDesc(Customer customer);
}
