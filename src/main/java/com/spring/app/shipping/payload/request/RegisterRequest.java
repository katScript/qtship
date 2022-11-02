package com.spring.app.shipping.payload.request;

public class RegisterRequest {
    private String username;
    private String password;
    private ShipperRequest data;

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

    public ShipperRequest getData() {
        return data;
    }

    public void setData(ShipperRequest data) {
        this.data = data;
    }
}
