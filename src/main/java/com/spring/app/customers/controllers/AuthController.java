package com.spring.app.customers.controllers;

import com.spring.app.authentication.models.Role;
import com.spring.app.authentication.models.User;
import com.spring.app.authentication.payload.request.LoginRequest;
import com.spring.app.authentication.payload.response.JwtResponse;
import com.spring.app.authentication.payload.response.MessageResponse;
import com.spring.app.authentication.models.repository.RoleRepository;
import com.spring.app.authentication.models.repository.UserRepository;
import com.spring.app.authentication.security.jwt.JwtUtils;
import com.spring.app.authentication.security.services.UserDetailsImpl;
import com.spring.app.customers.models.Address;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.ForControl;
import com.spring.app.customers.payload.request.auth.ForgotPasswordRequest;
import com.spring.app.customers.payload.request.auth.RegisterRequest;
import com.spring.app.customers.payload.request.auth.ResetPasswordRequest;
import com.spring.app.customers.payload.response.customer.ForgotPasswordResponse;
import com.spring.app.customers.models.repository.AddressRepository;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.customers.models.repository.ForControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController("CustomerAuthController")
@RequestMapping("/v1/customer/auth")
public class AuthController {
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

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/forget")
    public ResponseEntity<?> forgetPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        User user = userRepository.findByUsername(forgotPasswordRequest.getUsername()).orElse(null);

        if (user == null)
            return ResponseEntity.ok(new MessageResponse("Error: User account is not exists."));

        String jwt = jwtUtils.generateJwtTokenWithoutAuth(user);

        return ResponseEntity.ok(new ForgotPasswordResponse(jwt,
                user.getUsername(),
                user.getEmail())
        );
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        String username = jwtUtils.getUserNameFromJwtToken(resetPasswordRequest.getToken());

        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null)
            return ResponseEntity.ok(new MessageResponse("Error: User account is not exists."));

        Customer customer = customerRepository.findByUser(user)
                .orElse(null);

        if (customer == null)
            return ResponseEntity.ok(new MessageResponse("Error: Customer is not found."));

        user.setPassword(encoder.encode(resetPasswordRequest.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Password change success!"));
    }

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

            Role userRole = roleRepository.findByCode(Customer.ROLE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            user.addRole(userRole);

            Customer customer = new Customer(
                    registerRequest.getCustomer().getFullName(),
                    registerRequest.getCustomer().getPhone(),
                    registerRequest.getCustomer().getCompanyName(),
                    registerRequest.getCustomer().getEmail()
            );

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
