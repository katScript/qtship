package com.spring.app.customers.controllers;

import com.spring.app.authentication.models.Role;
import com.spring.app.authentication.models.User;
import com.spring.app.authentication.payload.request.SignupRequest;
import com.spring.app.authentication.payload.response.MessageResponse;
import com.spring.app.authentication.repository.RoleRepository;
import com.spring.app.authentication.repository.UserRepository;
import com.spring.app.authentication.security.jwt.JwtUtils;
import com.spring.app.customers.models.Address;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.ForControl;
import com.spring.app.customers.payload.request.RegisterRequest;
import com.spring.app.customers.repository.AddressRepository;
import com.spring.app.customers.repository.CustomerRepository;
import com.spring.app.customers.repository.ForControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/customer/auth")
public class CustomerController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ForControlRepository forControlRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        try {
            User user = new User(registerRequest.getUsername(),
                    encoder.encode(registerRequest.getPassword()),
                    registerRequest.getUsername());

            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByCode("customer")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);

            user.setRoles(roles);
            User newUser = userRepository.save(user);

            Customer customer = new Customer(
                    registerRequest.getCustomer().getFullName(),
                    newUser.getId(),
                    registerRequest.getCustomer().getPhone(),
                    registerRequest.getCustomer().getCompanyName(),
                    registerRequest.getCustomer().getEmail()
            );

            Customer newCustomer = customerRepository.save(customer);

            Address address = new Address(
                    registerRequest.getCustomerAddress().getProvince(),
                    registerRequest.getCustomerAddress().getProvinceId(),
                    registerRequest.getCustomerAddress().getDistrict(),
                    registerRequest.getCustomerAddress().getDistrictId(),
                    registerRequest.getCustomerAddress().getWard(),
                    registerRequest.getCustomerAddress().getWardId(),
                    registerRequest.getCustomerAddress().getStreet(),
                    false
            );

            address.setCustomer(newCustomer);
            addressRepository.save(address);

            ForControl forControl = new ForControl(
                    registerRequest.getForControl().getHolderName(),
                    registerRequest.getForControl().getCardNumber(),
                    registerRequest.getForControl().getBank(),
                    registerRequest.getForControl().getAddress()
            );

            forControl.setCustomer(newCustomer);
            forControlRepository.save(forControl);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
