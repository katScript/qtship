package com.spring.app.customers.controllers;

import com.spring.app.authentication.models.User;
import com.spring.app.authentication.payload.response.MessageResponse;
import com.spring.app.authentication.repository.UserRepository;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.payload.request.customer.SaveCustomerRequest;
import com.spring.app.customers.payload.response.customer.DetailResponse;
import com.spring.app.customers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> customerDetail(@Valid @PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElse(null);

        if (user == null)
            return ResponseEntity.ok(new MessageResponse("Error: User account is not exists."));

        Customer customer = customerRepository.findByUser(user)
                .orElse(null);


        if (customer == null)
            return ResponseEntity.ok(new MessageResponse("Error: Customer is not found."));

        return ResponseEntity.ok(new DetailResponse(customer));
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody com.spring.app.customers.payload.helperData.Customer customerData) {
        User user = userRepository.findByUsername(customerData.getUserName())
                .orElse(null);

        if (user == null)
            return ResponseEntity.ok(new MessageResponse("Error: User account is not exists."));

        Customer customer = customerRepository.findByUser(user)
                .orElse(null);

        if (customer == null)
            return ResponseEntity.ok(new MessageResponse("Error: Customer is not found."));

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


//    @PostMapping("/address/save")
//    public ResponseEntity<?> saveAddress(@Valid @RequestBody SaveAddressRequest addressRequest) {
//        Customer customer = customerRepository.findById(addressRequest.getCustomerId())
//                .orElse(null);
//
//        if (customer == null)
//            return ResponseEntity.ok(new MessageResponse("Error: Customer is not found."));
//
//        Address address = new Address(
//                addressRequest.getAddress().getProvince(),
//                addressRequest.getAddress().getProvinceId(),
//                addressRequest.getAddress().getDistrict(),
//                addressRequest.getAddress().getDistrictId(),
//                addressRequest.getAddress().getWard(),
//                addressRequest.getAddress().getWardId(),
//                addressRequest.getAddress().getStreet(),
//                addressRequest.getAddress().getPrimary()
//        );
//
//
//
//    }

}
