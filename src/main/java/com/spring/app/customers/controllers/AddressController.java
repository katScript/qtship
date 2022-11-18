package com.spring.app.customers.controllers;

import com.spring.app.customers.models.Address;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.AddressRepository;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.customers.payload.AddressData;
import com.spring.app.payload.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/customer/address")
public class AddressController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressRepository addressRepository;

    @PostMapping("/save/customerId/{id}")
    public ResponseEntity<?> saveCustomerForControl(@Valid @PathVariable Long id, @RequestBody AddressData addressData) {
        Customer customer = customerRepository.findById(id).orElse(null);

        if (customer != null) {
            Address address;

            if (addressData.getId() != null) {
                address = addressRepository.findByIdAndCustomer(addressData.getId(), customer)
                        .orElseThrow(() -> new RuntimeException("ForControl not found!"));
            } else {
                address = new Address();
                address.setCustomer(customer);
            }

            address.setPrimary(addressData.getPrimary())
                    .setProvince(addressData.getProvince())
                    .setDistrict(addressData.getDistrict())
                    .setWard(addressData.getWard())
                    .setProvinceId(addressData.getProvinceId())
                    .setDistrictId(addressData.getDistrictId())
                    .setWardId(addressData.getWardId())
                    .setStreet(addressData.getStreet());

            addressRepository.save(address);
            return ResponseEntity.ok(new MessageResponse("Address save complete!"));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Customer not found!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteForControlById(@Valid @PathVariable Long id) {
        addressRepository.findById(id).ifPresent(address -> addressRepository.delete(address));

        return ResponseEntity.ok(new MessageResponse("Delete for control done!"));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@Valid @PathVariable Long id) {
        Address address = addressRepository.findById(id).orElse(null);

        if (address != null) {
            AddressData addressData = new AddressData(
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

            return ResponseEntity.ok(addressData);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("For control not found!"));
    }
}
