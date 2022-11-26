package com.spring.app.customers.controllers;

import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.ForControl;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.customers.models.repository.ForControlRepository;
import com.spring.app.customers.payload.ForControlData;
import com.spring.app.payload.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/customer/forControl")
public class ForControlController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ForControlRepository forControlRepository;

    @PostMapping("/save/customerId/{id}")
    public ResponseEntity<?> saveCustomerForControl(@Valid @PathVariable Long id, @RequestBody ForControlData forControlData) {
        try {
            Customer customer = customerRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Customer not found!"));

            ForControl forControl;

            if (forControlData.getId() != null) {
                forControl = forControlRepository.findByIdAndCustomer(forControlData.getId(), customer)
                        .orElseThrow(() -> new RuntimeException("ForControl not found!"));
            } else {
                forControl = new ForControl();
                forControl.setCustomer(customer);
            }

            forControl.setBank(forControlData.getBank())
                    .setAddress(forControlData.getAddress())
                    .setCardNumber(forControlData.getCardNumber())
                    .setHolderName(forControlData.getHolderName());

            forControlRepository.save(forControl);
            return ResponseEntity.ok(new MessageResponse("ForControl save complete!"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteForControlById(@Valid @PathVariable Long id) {
        forControlRepository.findById(id).ifPresent(forControl -> forControlRepository.delete(forControl));

        return ResponseEntity.ok(new MessageResponse("Delete for control done!"));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@Valid @PathVariable Long id) {
        ForControl forControl = forControlRepository.findById(id).orElse(null);

        if (forControl != null) {
            ForControlData forControlData = new ForControlData(
                    forControl.getId(),
                    forControl.getHolderName(),
                    forControl.getCardNumber(),
                    forControl.getBank(),
                    forControl.getAddress()
            );

            return ResponseEntity.ok(forControlData);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("For control not found!"));
    }
}
