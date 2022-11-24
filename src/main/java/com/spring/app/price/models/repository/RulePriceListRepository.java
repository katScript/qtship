package com.spring.app.price.models.repository;

import com.spring.app.price.models.Rule;
import com.spring.app.price.models.RulePriceList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RulePriceListRepository extends JpaRepository<RulePriceList, Long> {
    Optional<RulePriceList> findFirstByRuleAndAreaCode(Rule rule, String areaCode);
}
