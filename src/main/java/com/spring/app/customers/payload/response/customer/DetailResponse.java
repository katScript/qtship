package com.spring.app.customers.payload.response.customer;

import com.spring.app.customers.payload.Customer;

public class DetailResponse {
    private Customer customer;

    public DetailResponse (
            com.spring.app.customers.models.Customer customer
    ) {
        this.customer = new Customer(customer);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
