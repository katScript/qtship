package com.spring.app.shipping.controller;

import com.spring.app.authentication.models.User;
import com.spring.app.authentication.models.repository.UserRepository;
import com.spring.app.payload.MessageResponse;
import com.spring.app.shipping.models.Shipper;
import com.spring.app.shipping.models.repository.ShipperRepository;
import com.spring.app.shipping.payload.ShipperData;
import com.spring.app.shipping.payload.ShipperOrderData;
import com.spring.app.shipping.service.ShippingOrderService;
import com.spring.app.shipping.service.ShippingService;
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
    @Autowired
    ShippingService shippingService;
    @Autowired
    ShippingOrderService shippingOrderService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllShipper() {
        List<Shipper> shipperList = shipperRepository.findAll();
        List<ShipperData> shipperResponses = new ArrayList<>();

        for (Shipper sh : shipperList) {
            shipperResponses.add(shippingService.processShipperDataResponse(sh));
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

        return ResponseEntity.ok(shippingService.processShipperDataResponse(shipper));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveShipper(@Valid @RequestBody ShipperData shipperRequest) {
        Shipper shipper = shippingService.processShipperData(shipperRequest);
        shipperRepository.save(shipper);

        return ResponseEntity.ok(new MessageResponse("Save shipper data success!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteShipper(@Valid @PathVariable Long id) {
        Shipper shipper = shipperRepository.findById(id).orElseThrow(() -> new RuntimeException("Shipper not found!"));
        shipperRepository.delete(shipper);

        return ResponseEntity.ok(new MessageResponse("Delete shipper data success!"));
    }

    @GetMapping("/order/assign/{id}")
    public ResponseEntity<?> getAssignOrder(@Valid @PathVariable Long id) {
        List<ShipperOrderData> response = shippingOrderService.getListOrderAssign(id);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/order/accept")
    public ResponseEntity<?> acceptOrder(@Valid @RequestBody List<Long> ids) {
        for (Long id: ids) {
            this.shippingService.acceptOrder(id);
        }

        return ResponseEntity.ok(new MessageResponse("Accept done! Order has assign to shipper!"));
    }

    @PostMapping("/order/reject")
    public ResponseEntity<?> rejectOrder(@Valid @RequestBody List<Long> ids) {
        for (Long id: ids) {
            this.shippingService.rejectOrder(id);
        }

        return ResponseEntity.ok(new MessageResponse("Reject done!"));
    }
}
