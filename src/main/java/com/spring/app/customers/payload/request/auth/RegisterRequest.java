package com.spring.app.customers.payload.request.auth;

import com.spring.app.customers.payload.AddressData;
import com.spring.app.customers.payload.CustomerData;
import com.spring.app.customers.payload.ForControlData;

import javax.validation.constraints.*;

public class RegisterRequest {
    @NotNull(message = "Username is required!")
    private String username;
    @NotNull(message = "Password is required!")
    private String password;
    @NotNull(message = "Customer address is required!")
    private AddressData customerAddress;
    @NotNull(message = "Customer information is required!")
    private CustomerData customer;
    @NotNull(message = "Customer for control information is required!")
    private ForControlData forControl;

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

    public AddressData getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(AddressData customerAddress) {
        this.customerAddress = customerAddress;
    }

    public CustomerData getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerData customer) {
        this.customer = customer;
    }

    public ForControlData getForControl() {
        return forControl;
    }

    public void setForControl(ForControlData forControl) {
        this.forControl = forControl;
    }
}
