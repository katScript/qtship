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
import com.spring.app.helper.services.DateFormatHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ForControlRepository forControlRepository;

    public Customer processCustomerData(CustomerData data) {
        Customer customer;
        if (data.getId() != null) {
            customer = customerRepository.findById(data.getId()).orElseThrow(() -> new RuntimeException("Customer not exists!"));
        } else {
            customer = new Customer();
        }

        customer.setFullName(data.getFullName())
                .setGender(data.getGender())
                .setDob(
                        DateFormatHelper.stringToDate(data.getDob())
                )
                .setPhone(data.getPhone())
                .setCompanyName(data.getCompanyName())
                .setEmail(data.getEmail())
                .setCidBack(data.getCidBack())
                .setCidFront(data.getCidFront())
                .setSubscription(data.getSubscription());

        return customer;
    }

    public Address processAddressData(AddressData data) {
        Address address;
        if (data.getId() != null) {
            address = addressRepository.findById(data.getId()).orElseThrow(() -> new RuntimeException("Address not exists!"));
        } else {
            address = new Address();
        }

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
        ForControl forControl;

        if (data.getId() != null) {
            forControl = forControlRepository.findById(data.getId()).orElseThrow(() -> new RuntimeException("For control not exists!"));
        } else {
            forControl = new ForControl();
        }

        forControl.setHolderName(data.getHolderName())
                .setCardNumber(data.getCardNumber())
                .setBank(data.getBank())
                .setAddress(data.getAddress());

        return forControl;
    }

    public CustomerData processCustomerResponse(Customer customer) {
        Set<AddressData> addressDataSet = new HashSet<>();
        Set<ForControlData> forControlDataSet = new HashSet<>();

        for (Address address : customer.getAddressSet()) {
            AddressData aD = new AddressData(
                    address.getId(),
                    address.getProvince(),
                    address.getProvinceId(),
                    address.getDistrict(),
                    address.getDistrictId(),
                    address.getWard(),
                    address.getWardId(),
                    address.getStreet(),
                    address.getPrimary()
            );

            aD.setCreatedAt(
                    DateFormatHelper.dateToString(address.getCreatedAt())
            );
            aD.setUpdatedAt(
                    DateFormatHelper.dateToString(address.getUpdatedAt())
            );

            addressDataSet.add(aD);
        }

        for (ForControl forControl : customer.getForControls()) {
            ForControlData fD = new ForControlData(
                    forControl.getId(),
                    forControl.getHolderName(),
                    forControl.getCardNumber(),
                    forControl.getBank(),
                    forControl.getAddress()
            );

            fD.setCreatedAt(
                    DateFormatHelper.dateToString(forControl.getCreatedAt())
            );
            fD.setUpdatedAt(
                    DateFormatHelper.dateToString(forControl.getUpdatedAt())
            );

            forControlDataSet.add(fD);
        }

        CustomerData res = new CustomerData(
                customer.getId(),
                customer.getCustomerId(),
                customer.getUser().getUsername(),
                customer.getFullName(),
                customer.getGender(),
                DateFormatHelper.dateToString(customer.getDob()),
                customer.getPhone(),
                customer.getCompanyName(),
                customer.getEmail(),
                customer.getCidFront(),
                customer.getCidBack(),
                customer.getSubscription(),
                addressDataSet,
                forControlDataSet
        );

        res.setCreatedAt(
                DateFormatHelper.dateToString(customer.getCreatedAt())
        );

        res.setUpdatedAt(
                DateFormatHelper.dateToString(customer.getUpdatedAt())
        );

        return res;
    }

}
