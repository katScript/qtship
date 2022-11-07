package com.spring.app.customers.controllers;

import com.spring.app.authentication.models.Role;
import com.spring.app.authentication.models.User;
import com.spring.app.customers.service.CustomerService;
import com.spring.app.payload.MessageResponse;
import com.spring.app.authentication.models.repository.RoleRepository;
import com.spring.app.authentication.models.repository.UserRepository;
import com.spring.app.authentication.security.jwt.JwtUtils;
import com.spring.app.customers.models.Address;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.ForControl;
import com.spring.app.customers.payload.request.auth.RegisterRequest;
import com.spring.app.customers.payload.request.auth.ResetPasswordRequest;
import com.spring.app.customers.models.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController("CustomerAuthController")
@RequestMapping("/v1/customer/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    CustomerService customerService;

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        String username = jwtUtils.getUserNameFromJwtToken(resetPasswordRequest.getToken());

        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("User account is not exists."));

        Customer customer = customerRepository.findByUser(user)
                .orElse(null);

        if (customer == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Customer is not found."));

        user.setPassword(encoder.encode(resetPasswordRequest.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Password change success!"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        try {
            User user = new User(registerRequest.getUsername(),
                    encoder.encode(registerRequest.getPassword()),
                    registerRequest.getUsername());

            Role userRole = roleRepository.findByCode(Customer.ROLE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            user.addRole(userRole);

            Customer customer = customerService
                    .processCustomerData(registerRequest.getCustomer());

            registerRequest.getCustomerAddress().setPrimary(true);
            Address address = customerService
                    .processAddressData(registerRequest.getCustomerAddress());

            ForControl forControl = customerService
                    .processForControlData(registerRequest.getForControl());

            address.setCustomer(customer);
            forControl.setCustomer(customer);

            customer.setUser(user)
                    .addAddress(address)
                    .addForControls(forControl);

            customerRepository.save(customer);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(new MessageResponse("Customer registered successfully!"));
    }
}
