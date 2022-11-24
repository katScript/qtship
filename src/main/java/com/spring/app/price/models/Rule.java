package com.spring.app.price.models;

import com.spring.app.office.models.Office;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="price_rule")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "rule", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RuleArea> ruleAreas;

    @OneToMany(mappedBy = "rule", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RulePriceList> rulePriceLists;

    @OneToMany(mappedBy = "priceRule",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Office> offices;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public Rule() {
        this.ruleAreas = new HashSet<>();
        this.rulePriceLists = new HashSet<>();
        this.offices = new HashSet<>();
    }
    
    public Rule(
            String name,
            String description,
            Set<RuleArea> ruleAreas,
            Set<RulePriceList> rulePriceLists,
            Set<Office> offices
    ) {
        this.name = name;
        this.description = description;
        this.ruleAreas = ruleAreas;
        this.rulePriceLists = rulePriceLists;
        this.offices = offices;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Rule setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Rule setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<RuleArea> getRuleAreas() {
        return ruleAreas;
    }

    public Rule setRuleAreas(Set<RuleArea> ruleAreas) {
        this.ruleAreas = ruleAreas;
        return this;
    }
    
    public Set<RulePriceList> getRulePriceLists() {
        return rulePriceLists;
    }

    public Rule setRulePriceLists(Set<RulePriceList> rulePriceLists) {
        this.rulePriceLists = rulePriceLists;
        return this;
    }

    public Set<Office> getOffices() {
        return offices;
    }

    public Rule setOffices(Set<Office> offices) {
        this.offices = offices;
        return this;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
