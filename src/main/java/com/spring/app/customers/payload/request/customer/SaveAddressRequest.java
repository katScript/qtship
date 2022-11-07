package com.spring.app.customers.payload.request.customer;

import com.spring.app.customers.payload.AddressData;

public class SaveAddressRequest {
    private Long customerId;
    private AddressData address;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public AddressData getAddress() {
        return address;
    }

    public void setAddress(AddressData address) {
        this.address = address;
    }
}
