package com.spring.app.shipping.controller;

import com.spring.app.authentication.models.Role;
import com.spring.app.authentication.models.User;
import com.spring.app.authentication.models.repository.RoleRepository;
import com.spring.app.authentication.models.repository.UserRepository;
import com.spring.app.authentication.security.jwt.JwtUtils;
import com.spring.app.payload.MessageResponse;
import com.spring.app.shipping.models.Shipper;
import com.spring.app.shipping.models.repository.ShipperRepository;
import com.spring.app.shipping.payload.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/shipper")
public class ShipperController {
    @Autowired
    ShipperRepository shipperRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerShipper(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        try {
            User user = new User(registerRequest.getUsername(),
                    encoder.encode(registerRequest.getPassword()),
                    registerRequest.getUsername());

            Role userRole = roleRepository.findByCode(Shipper.ROLE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            user.addRole(userRole);

            Shipper shipper = new Shipper(
                    registerRequest.getData().getFullName(),
                    registerRequest.getData().getEmail(),
                    registerRequest.getData().getAddress(),
                    registerRequest.getData().getCurrentAddress(),
                    user
            );

            this.shipperRepository.save(shipper);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(new MessageResponse("Shipper registered successfully!"));
    }


}
