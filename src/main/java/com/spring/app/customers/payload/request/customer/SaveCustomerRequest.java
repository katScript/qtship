package com.spring.app.customers.payload.request.customer;

import com.spring.app.customers.payload.helperData.Customer;

public class SaveCustomerRequest {
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
