package com.spring.app.admin.user.payload.request;

import com.spring.app.admin.user.payload.AdminData;

public class RegisterRequest {
    private String userName;
    private String password;
    private AdminData adminData;

    public RegisterRequest() {}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminData getAdminData() {
        return adminData;
    }

    public void setAdminData(AdminData adminData) {
        this.adminData = adminData;
    }
}
