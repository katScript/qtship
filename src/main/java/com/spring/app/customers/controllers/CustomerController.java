package com.spring.app.customers.controllers;

import com.spring.app.authentication.models.User;
import com.spring.app.customers.payload.CustomerData;
import com.spring.app.customers.payload.request.customer.UpdateCidRequest;
import com.spring.app.customers.service.CustomerService;
import com.spring.app.payload.CustomPageResponse;
import com.spring.app.payload.CustomerFilterRequest;
import com.spring.app.payload.FilterRequest;
import com.spring.app.payload.MessageResponse;
import com.spring.app.authentication.models.repository.UserRepository;
import com.spring.app.customers.models.Address;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.payload.request.customer.SaveAddressRequest;
import com.spring.app.customers.models.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<?> getAllCustomer(
            @Valid CustomerFilterRequest fR
    ) {
        try {
            Pageable pageable = PageRequest.of(fR.getPage(), fR.getSize());
            Long id = fR.getCode() != null ? Long.parseLong(fR.getCode()) : null;

            Page<Customer> customerList = customerRepository.findAllWithFilter(
                    id,
                    fR.getName(),
                    fR.getCompany(),
                    fR.getPhone(),
                    fR.getEmail(),
                    pageable
            );
            CustomPageResponse pageResponse = new CustomPageResponse(customerList);

            List<CustomerData> content = new ArrayList<>();

            for (Customer c: customerList) {
                content.add(customerService.processCustomerResponse(c));
            }

            pageResponse.setContent(content);
            return ResponseEntity.ok(pageResponse);
        } catch (NumberFormatException e) {
            return ResponseEntity.ok(new CustomPageResponse());
        }
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
}
