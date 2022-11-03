package com.spring.app.shipping.controller;

import com.spring.app.authentication.models.User;
import com.spring.app.authentication.models.repository.UserRepository;
import com.spring.app.payload.MessageResponse;
import com.spring.app.shipping.models.Shipper;
import com.spring.app.shipping.models.repository.ShipperRepository;
import com.spring.app.shipping.payload.request.ShipperRequest;
import com.spring.app.shipping.payload.response.ShipperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/shipper")
public class ShipperController {
    @Autowired
    ShipperRepository shipperRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllShipper() {
        List<Shipper> shipperList = shipperRepository.findAll();
        List<ShipperResponse> shipperResponses = new ArrayList<>();

        for (Shipper sh : shipperList) {
            shipperResponses.add(new ShipperResponse(
                    sh.getId(),
                    sh.getShipperCode(),
                    sh.getFullName(),
                    sh.getEmail(),
                    sh.getPhone(),
                    sh.getAddress(),
                    sh.getCurrentAddress(),
                    sh.getCreatedAt()
            ));
        }

        return ResponseEntity.ok(shipperResponses);
    }

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
                .orElseThrow(() -> new RuntimeException("Can not find shipper!"));

        shipper.setFullName(shipperRequest.getFullName())
                .setEmail(shipperRequest.getEmail())
                .setPhone(shipperRequest.getPhone())
                .setAddress(shipperRequest.getAddress())
                .setCurrentAddress(shipperRequest.getCurrentAddress());

        shipperRepository.save(shipper);

        return ResponseEntity.ok(new MessageResponse("Save shipper data success!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteShipper(@Valid @PathVariable Long id) {
        Shipper shipper = shipperRepository.findById(id).orElseThrow(() -> new RuntimeException("Shipper not found!"));

        shipperRepository.delete(shipper);

        return ResponseEntity.ok(new MessageResponse("Delete shipper data success!"));

    }
}
