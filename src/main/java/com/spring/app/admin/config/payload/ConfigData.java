package com.spring.app.admin.config.payload;

public class ConfigData {
    private Long id;
    private String key;
    private String scope;
    private String type;
    private String value;
    private String createdAt;
    private String updatedAt;

    public ConfigData() {}

    public ConfigData(
            Long id,
            String key,
            String scope,
            String type,
            String value,
            String createdAt,
            String updatedAt
    ) {
        this.id = id;
        this.key = key;
        this.scope = scope;
        this.type = type;
        this.value = value;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
