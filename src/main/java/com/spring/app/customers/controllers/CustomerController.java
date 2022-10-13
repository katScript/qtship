package com.spring.app.customers.controllers;

import com.spring.app.authentication.models.Role;
import com.spring.app.authentication.models.User;
import com.spring.app.authentication.payload.request.LoginRequest;
import com.spring.app.authentication.payload.response.JwtResponse;
import com.spring.app.authentication.payload.response.MessageResponse;
import com.spring.app.authentication.repository.RoleRepository;
import com.spring.app.authentication.repository.UserRepository;
import com.spring.app.authentication.security.jwt.JwtUtils;
import com.spring.app.authentication.security.services.UserDetailsImpl;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = userRepository.getById(userDetails.getId());
        List<Customer> customers = customerRepository.findByUser(user);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        User _user = null;

        try {
            User user = new User(registerRequest.getUsername(),
                    encoder.encode(registerRequest.getPassword()),
                    registerRequest.getUsername());

            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByCode(Customer.ROLE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);

            user.setRoles(roles);
            _user = userRepository.save(user);

            Customer customer = new Customer(
                    registerRequest.getCustomer().getFullName(),
                    registerRequest.getCustomer().getPhone(),
                    registerRequest.getCustomer().getCompanyName(),
                    registerRequest.getCustomer().getEmail()
            );

            customer.setUser(_user);
            Customer _customer = customerRepository.save(customer);

            Address address = new Address(
                    registerRequest.getCustomerAddress().getProvince(),
                    registerRequest.getCustomerAddress().getProvinceId(),
                    registerRequest.getCustomerAddress().getDistrict(),
                    registerRequest.getCustomerAddress().getDistrictId(),
                    registerRequest.getCustomerAddress().getWard(),
                    registerRequest.getCustomerAddress().getWardId(),
                    registerRequest.getCustomerAddress().getStreet(),
                    true
            );

            ForControl forControl = new ForControl(
                    registerRequest.getForControl().getHolderName(),
                    registerRequest.getForControl().getCardNumber(),
                    registerRequest.getForControl().getBank(),
                    registerRequest.getForControl().getAddress()
            );

            address.setCustomer(_customer);
            forControl.setCustomer(_customer);

            addressRepository.save(address);
            forControlRepository.save(forControl);
        } catch (RuntimeException e) {
            if (_user != null) userRepository.delete(_user);

            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(new MessageResponse("Customer registered successfully!"));
    }
}
