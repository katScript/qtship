package com.spring.app.shipping.controller;

import com.spring.app.authentication.models.Role;
import com.spring.app.authentication.models.User;
import com.spring.app.authentication.models.repository.UserRepository;
import com.spring.app.customers.models.Address;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.ForControl;
import com.spring.app.customers.payload.request.auth.RegisterRequest;
import com.spring.app.payload.MessageResponse;
import com.spring.app.shipping.models.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register")
    public ResponseEntity<?> registerShipper(@Valid @RequestBody RegisterRequest registerRequest) {


        return ResponseEntity.ok(new MessageResponse("Shipper registered successfully!"));
    }
}
