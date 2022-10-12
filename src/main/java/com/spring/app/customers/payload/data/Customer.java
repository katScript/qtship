package com.spring.app.customers.payload.data;

import javax.validation.constraints.*;

public class Customer {
    @NotNull(message = "Customer name is required!")
    String fullName;
    @NotNull(message = "Customer phone number is required!")
    @Pattern(regexp="^\\\\d{10}$", message = "Phone number not valid!")
    String phone;
    @NotNull(message = "Customer company is required!")
    String companyName;
    @NotNull(message = "Customer email is required!")
    String email;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
