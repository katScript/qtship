package com.spring.app.shipping.controller;

import com.spring.app.authentication.models.User;
import com.spring.app.authentication.models.repository.RoleRepository;
import com.spring.app.authentication.models.repository.UserRepository;
import com.spring.app.authentication.security.jwt.JwtUtils;
import com.spring.app.payload.MessageResponse;
import com.spring.app.shipping.models.Shipper;
import com.spring.app.shipping.models.repository.ShipperRepository;
import com.spring.app.shipping.payload.request.ShipperRequest;
import com.spring.app.shipping.payload.response.ShipperResponse;
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

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getShippingDetail(@Valid @PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElse(null);

        if (user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User account is not exists."));

        Shipper shipper = shipperRepository.findByUser(user).orElse(null);

        if (shipper == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Shipper account is not exists."));

        return ResponseEntity.ok(new ShipperResponse(
                shipper.getId(),
                shipper.getShipperCode(),
                shipper.getFullName(),
                shipper.getEmail(),
                shipper.getPhone(),
                shipper.getAddress(),
                shipper.getCurrentAddress(),
                shipper.getCreatedAt()
        ));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveShipper(@Valid @RequestBody ShipperRequest shipperRequest) {
        Shipper shipper = shipperRepository.findById(shipperRequest.getId())
                .orElse(new Shipper());

        shipper.setFullName(shipperRequest.getFullName())
                .setEmail(shipperRequest.getEmail())
                .setPhone(shipperRequest.getPhone())
                .setAddress(shipperRequest.getAddress())
                .setCurrentAddress(shipperRequest.getCurrentAddress());

        shipperRepository.save(shipper);

        return ResponseEntity.ok(new MessageResponse("Save shipper data success!"));
    }
}
