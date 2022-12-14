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
import com.spring.app.customers.payload.request.customer.UpdateCidRequest;
import com.spring.app.helper.date.DateFormatHelper;
import com.spring.app.helper.file.services.image.ImageStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerService {
    public static final String SCOPE = "customer";
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ForControlRepository forControlRepository;
    @Autowired
    ImageStorageServiceImpl storageService;

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

        processCustomerCid(customer, res);

        return res;
    }

    public void saveCustomerCid(UpdateCidRequest cid) {
        Customer customer = customerRepository.findById(cid.getId()).orElseThrow(() -> new RuntimeException("Customer not exists!"));

        customer.setCidFront(
                processUploadCustomerImage(cid.getCidFront(), customer.getCustomerId(), "cidFront", customer.getCidFront())
        ).setCidBack(
                processUploadCustomerImage(cid.getCidBack(), customer.getCustomerId(), "cidBack", customer.getCidBack())
        );

        customerRepository.save(customer);
    }

    public String processUploadCustomerImage(MultipartFile file, String customerCode, String name, String current) {
        Resource resource = null;

        if (file != null && !file.isEmpty()) {
            storageService.setPath(getImageScope(customerCode));
            resource = storageService.save(file, name);
        }

        return resource != null ? resource.getFilename() : current;
    }

    public String getImageScope(String path) {
        return SCOPE + File.separator + path;
    }

    public void processCustomerCid(Customer customer, CustomerData customerData) {
        String cidFront = customer.getCidFront() == null ?
                getImageUrl(ImageStorageServiceImpl.DEFAULT) :
                getImageUrl(customer.getCustomerId() + File.separator + customer.getCidFront());

        String cidBack = customer.getCidBack() == null ?
                getImageUrl(ImageStorageServiceImpl.DEFAULT) :
                getImageUrl(customer.getCustomerId() + File.separator + customer.getCidBack());

        customerData.setCidFront(cidFront);
        customerData.setCidBack(cidBack);
    }

    public String getImageUrl(String path) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build().toUriString()
                + "/image/customer/" + path;
    }
}
