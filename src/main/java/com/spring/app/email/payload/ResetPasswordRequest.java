package com.spring.app.email.payload;

public class ResetPasswordRequest {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
