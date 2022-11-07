package com.spring.app.customers.payload.response.customer;

import com.spring.app.customers.payload.CustomerData;

public class DetailResponse {
    private CustomerData customer;

    public DetailResponse (
            com.spring.app.customers.models.Customer customer
    ) {
        this.customer = new CustomerData(customer);
    }

    public CustomerData getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerData customer) {
        this.customer = customer;
    }
}
