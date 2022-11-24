package com.spring.app.price.models.repository;

import com.spring.app.price.models.Rule;
import com.spring.app.price.models.RuleArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RuleAreaRepository extends JpaRepository<RuleArea, Long> {
    List<RuleArea> findByRule(Rule rule);

    Optional<RuleArea> findFirstByRuleAndProvinceId(Rule rule, String provinceId);
}
