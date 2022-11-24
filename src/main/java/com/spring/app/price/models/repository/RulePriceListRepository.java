package com.spring.app.price.models.repository;

import com.spring.app.price.models.RulePriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RulePriceListRepository extends JpaRepository<RulePriceList, Long> {
}
