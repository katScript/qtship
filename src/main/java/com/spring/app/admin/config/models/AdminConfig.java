package com.spring.app.admin.config.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="admin_config")
public class AdminConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", insertable = false, updatable = false)
    private Long id;

    @Column(name = "`key`")
    private String key;

    @Column(name = "scope")
    private String scope;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private String value;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    public AdminConfig() {}

    public AdminConfig(
            String key,
            String scope,
            String type,
            String value
    ) {
        this.key = key;
        this.scope = scope;
        this.type = type;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public AdminConfig setId(Long id) {
        this.id = id;
        return this;
    }

    public String getKey() {
        return key;
    }

    public AdminConfig setKey(String key) {
        this.key = key;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public AdminConfig setScope(String scope) {
        this.scope = scope;
        return this;
    }

    public String getType() {
        return type;
    }

    public AdminConfig setType(String type) {
        this.type = type;
        return this;
    }

    public String getValue() {
        return value;
    }

    public AdminConfig setValue(String value) {
        this.value = value;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
