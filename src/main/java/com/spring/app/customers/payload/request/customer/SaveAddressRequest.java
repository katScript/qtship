package com.spring.app.customers.payload.request.customer;

import com.spring.app.customers.payload.helperData.Address;

public class SaveAddressRequest {
    private Long customerId;
    private Address address;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
