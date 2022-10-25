package com.spring.app.customers.payload.request.auth;

import com.spring.app.customers.payload.Address;
import com.spring.app.customers.payload.Customer;
import com.spring.app.customers.payload.ForControl;

import javax.validation.constraints.*;

public class RegisterRequest {
    @NotNull(message = "Username is required!")
    private String username;
    @NotNull(message = "Password is required!")
    private String password;
    @NotNull(message = "Customer address is required!")
    private Address customerAddress;
    @NotNull(message = "Customer information is required!")
    private Customer customer;
    @NotNull(message = "Customer for control information is required!")
    private ForControl forControl;

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

    public Address getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(Address customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ForControl getForControl() {
        return forControl;
    }

    public void setForControl(ForControl forControl) {
        this.forControl = forControl;
    }
}
