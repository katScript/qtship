package com.spring.app.customers.payload.request.customer;

import org.springframework.web.multipart.MultipartFile;

public class UpdateCidRequest {
    private Long id;
    private MultipartFile cidFront;
    private MultipartFile cidBack;

    public MultipartFile getCidFront() {
        return cidFront;
    }

    public void setCidFront(MultipartFile cidFront) {
        this.cidFront = cidFront;
    }

    public MultipartFile getCidBack() {
        return cidBack;
    }

    public void setCidBack(MultipartFile cidBack) {
        this.cidBack = cidBack;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
