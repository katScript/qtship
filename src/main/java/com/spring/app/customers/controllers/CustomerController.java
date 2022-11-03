package com.spring.app.customers.controllers;

import com.spring.app.authentication.models.User;
import com.spring.app.payload.MessageResponse;
import com.spring.app.authentication.models.repository.UserRepository;
import com.spring.app.customers.models.Address;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.payload.request.customer.SaveAddressRequest;
import com.spring.app.customers.payload.response.customer.DetailResponse;
import com.spring.app.customers.models.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/all")
    public ResponseEntity<?> getAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        List<DetailResponse> responses = new ArrayList<>();

        for (Customer c: customerList) {
            responses.add(new DetailResponse(c));
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

        return ResponseEntity.ok(new DetailResponse(customer));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@Valid @PathVariable Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));

        customerRepository.delete(customer);
        return ResponseEntity.ok(new MessageResponse("Delete customer success!"));
    }

    @PostMapping("/save")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody com.spring.app.customers.payload.Customer customerData) {
        User user = userRepository.findByUsername(customerData.getUserName())
                .orElse(null);

        if (user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User account is not exists."));

        Customer customer = customerRepository.findByUser(user)
                .orElse(null);

        if (customer == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Customer is not found."));

        customer.setFullName(customerData.getFullName())
                .setGender(customerData.getGender())
                .setDob(customerData.getDob())
                .setPhone(customerData.getPhone())
                .setCompanyName(customerData.getCompanyName())
                .setEmail(customerData.getEmail())
                .setCidFront(customerData.getCidFront())
                .setCidBack(customerData.getCidBack())
                .setSubscription(customerData.getSubscription());

        customerRepository.save(customer);

        return ResponseEntity.ok(new MessageResponse("Save success!"));
    }

    @PostMapping("/address/save")
    public ResponseEntity<?> saveAddress(@Valid @RequestBody SaveAddressRequest addressRequest) {
        Customer customer = customerRepository.findById(addressRequest.getCustomerId())
                .orElse(null);

        if (customer == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Customer is not found."));

        Address address = new Address(
                addressRequest.getAddress().getProvince(),
                addressRequest.getAddress().getProvinceId(),
                addressRequest.getAddress().getDistrict(),
                addressRequest.getAddress().getDistrictId(),
                addressRequest.getAddress().getWard(),
                addressRequest.getAddress().getWardId(),
                addressRequest.getAddress().getStreet(),
                addressRequest.getAddress().getPrimary()
        );

        customer.addAddress(address);
        customerRepository.saveAndFlush(customer);

        return ResponseEntity.ok(new MessageResponse("Save success!"));
    }
}
