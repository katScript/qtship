package com.spring.app.customers.payload.request.customer;

public class DetailRequest {
    private long userId;
    public long userId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
