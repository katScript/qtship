package com.spring.app.customers.service;

import com.spring.app.customers.models.Address;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.ForControl;
import com.spring.app.customers.models.repository.AddressRepository;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.customers.models.repository.ForControlRepository;
import com.spring.app.customers.payload.AddressData;
import com.spring.app.customers.payload.CustomerData;
import com.spring.app.customers.payload.ForControlData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ForControlRepository forControlRepository;

    public Customer processCustomerData(CustomerData data) {
        Customer customer = customerRepository.findById(data.getId())
                .orElse(new Customer());

        customer.setFullName(data.getFullName())
                .setGender(data.getGender())
                .setDob(data.getDob())
                .setPhone(data.getPhone())
                .setCompanyName(data.getCompanyName())
                .setEmail(data.getEmail())
                .setCidBack(data.getCidBack())
                .setCidFront(data.getCidFront())
                .setSubscription(data.getSubscription());

        return customer;
    }

    public Address processAddressData(AddressData data) {
        Address address = addressRepository.findById(data.getId())
                .orElse(new Address());

        address.setPrimary(data.getPrimary())
                .setProvince(data.getProvince())
                .setDistrict(data.getDistrict())
                .setWard(data.getWard())
                .setProvinceId(data.getProvinceId())
                .setDistrictId(data.getDistrictId())
                .setWardId(data.getWardId())
                .setStreet(data.getStreet());

        return address;
    }

    public ForControl processForControlData(ForControlData data) {
        ForControl forControl = forControlRepository.findById(data.getId())
                .orElse(new ForControl());

        forControl.setHolderName(data.getHolderName())
                .setCardNumber(data.getCardNumber())
                .setBank(data.getBank())
                .setAddress(data.getAddress());

        return forControl;
    }

    public CustomerData processCustomerResponse(Customer customer) {
        CustomerData res = new CustomerData();

        return res;
    }

}
