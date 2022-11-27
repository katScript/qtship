package com.spring.app.price.payload.request;

import org.springframework.web.multipart.MultipartFile;

public class PriceRuleImportRequest {
    private Long id;
    private String name;
    private String description;
    private MultipartFile areaConfig;
    private MultipartFile priceConfig;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getAreaConfig() {
        return areaConfig;
    }

    public void setAreaConfig(MultipartFile areaConfig) {
        this.areaConfig = areaConfig;
    }

    public MultipartFile getPriceConfig() {
        return priceConfig;
    }

    public void setPriceConfig(MultipartFile priceConfig) {
        this.priceConfig = priceConfig;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
