package com.spring.app.customers.controllers;

import com.spring.app.authentication.models.User;
import com.spring.app.customers.payload.CustomerData;
import com.spring.app.customers.payload.request.customer.UpdateCidRequest;
import com.spring.app.customers.service.CustomerService;
import com.spring.app.payload.MessageResponse;
import com.spring.app.authentication.models.repository.UserRepository;
import com.spring.app.customers.models.Address;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.payload.request.customer.SaveAddressRequest;
import com.spring.app.customers.models.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/customer")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerData> responses = new ArrayList<>();

        for (Customer c: customerList) {
            responses.add(customerService.processCustomerResponse(c));
        }

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> customerDetail(@Valid @PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElse(null);

        if (user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User account is not exists."));

        Customer customer = customerRepository.findByUser(user)
                .orElse(null);

        if (customer == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Customer is not found."));

        return ResponseEntity.ok(customerService.processCustomerResponse(customer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@Valid @PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customerRepository.delete(customer);
        return ResponseEntity.ok(new MessageResponse("Delete customer success!"));
    }

    @PostMapping("/save")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody CustomerData customerData) {
        User user = userRepository.findByUsername(customerData.getUserName())
                .orElse(null);

        if (user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User account is not exists."));

        Customer customer = customerService.processCustomerData(customerData);
        customerRepository.save(customer);

        return ResponseEntity.ok(new MessageResponse("Save success!"));
    }

    @PostMapping(value ="/cid/save",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> saveCustomerCid(@Valid UpdateCidRequest updateCidRequest) {
        try {
            customerService.saveCustomerCid(updateCidRequest);
            return ResponseEntity.ok(new MessageResponse("Save customer identity success!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/address/save")
    public ResponseEntity<?> saveAddress(@Valid @RequestBody SaveAddressRequest addressRequest) {
        Customer customer = customerRepository.findById(addressRequest.getCustomerId())
                .orElse(null);

        if (customer == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Customer is not found."));

        Address address = new Address();

//        address.setProvince(addressRequest.getAddress().getProvince())
//                        .setPro
//                addressRequest.getAddress().getProvinceId(),
//                addressRequest.getAddress().getDistrict(),
//                addressRequest.getAddress().getDistrictId(),
//                addressRequest.getAddress().getWard(),
//                addressRequest.getAddress().getWardId(),
//                addressRequest.getAddress().getStreet(),
//                addressRequest.getAddress().getPrimary()

        customer.addAddress(address);
        customerRepository.saveAndFlush(customer);

        return ResponseEntity.ok(new MessageResponse("Save success!"));
    }
}
