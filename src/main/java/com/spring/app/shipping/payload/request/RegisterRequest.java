package com.spring.app.shipping.payload.request;

import com.spring.app.shipping.payload.ShipperData;

public class RegisterRequest {
    private String username;
    private String password;
    private ShipperData data;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ShipperData getData() {
        return data;
    }

    public void setData(ShipperData data) {
        this.data = data;
    }
}
