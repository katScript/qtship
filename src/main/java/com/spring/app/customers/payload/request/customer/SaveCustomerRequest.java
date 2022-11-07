package com.spring.app.customers.payload.request.customer;

import com.spring.app.customers.payload.CustomerData;

public class SaveCustomerRequest {
    private CustomerData customer;

    public CustomerData getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerData customer) {
        this.customer = customer;
    }
}
