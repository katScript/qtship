package com.spring.app.customers.payload.request;

import com.spring.app.customers.payload.data.Address;
import com.spring.app.customers.payload.data.Customer;
import com.spring.app.customers.payload.data.ForControl;

public class RegisterRequest {
    private String username;
    private String password;
    private Address customerAddress;
    private Customer customer;
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
