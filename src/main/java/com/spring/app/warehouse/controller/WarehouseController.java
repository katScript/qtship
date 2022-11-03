package com.spring.app.warehouse.controller;

import com.spring.app.payload.MessageResponse;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.warehouse.models.Warehouse;
import com.spring.app.warehouse.models.repository.WarehouseRepository;
import com.spring.app.customers.payload.request.DeleteRequest;
import com.spring.app.warehouse.payload.request.WarehouseDataRequest;
import com.spring.app.warehouse.payload.response.WarehouseListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/warehouse")
public class WarehouseController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllWarehouse() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        List<WarehouseListResponse> warehousesResponse = new ArrayList<>();

        for (Warehouse w: warehouses) {
            warehousesResponse.add(new WarehouseListResponse(
                    w.getId(),
                    w.getName(),
                    w.getAddress(),
                    w.getPhone()
            ));
        }

        return ResponseEntity.ok(warehousesResponse);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@Valid @PathVariable Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find warehouse"));

        WarehouseListResponse warehouseListResponse = new WarehouseListResponse(
                warehouse.getId(),
                warehouse.getName(),
                warehouse.getAddress(),
                warehouse.getPhone()
        );

        return ResponseEntity.ok(warehouseListResponse);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveWarehouse(@Valid @RequestBody WarehouseDataRequest warehouseDataRequest) {
        Customer customer = customerRepository.findById(warehouseDataRequest.getCustomerId())
                                .orElse(null);

        if (customer != null) {
            Warehouse warehouse;

            if (warehouseDataRequest.getId() != null) {
                warehouse = warehouseRepository.findById(warehouseDataRequest.getId())
                        .orElseThrow(() -> new RuntimeException("Warehouse not found!"));
            } else {
                warehouse = new Warehouse(customer);
            }

            warehouse.setName(warehouseDataRequest.getName())
                    .setPhone(warehouseDataRequest.getPhone())
                    .setAddress(warehouseDataRequest.getAddress());

            warehouseRepository.save(warehouse);

            return ResponseEntity.ok(new MessageResponse("Warehouse save successfully!"));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Customer not found!"));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteWarehouse(@Valid @RequestBody DeleteRequest deleteRequest) {
        Customer customer = customerRepository.findById(deleteRequest.getCustomerId())
                .orElse(null);

        if (customer != null) {
            Warehouse warehouse = warehouseRepository.findById(deleteRequest.getId()).orElse(null);

            if (warehouse == null)
                return ResponseEntity.badRequest().body(new MessageResponse("Warehouse not found!"));

            if (!warehouse.getCustomer().getId().equals(customer.getId()))
                return ResponseEntity.badRequest().body(new MessageResponse("Can't delete this warehouse! Customer not valid!"));

            warehouseRepository.delete(warehouse);
            return ResponseEntity.ok(new MessageResponse("Warehouse deleted successfully!"));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Customer not found!"));
    }

    @GetMapping("/all/customer/{id}")
    public ResponseEntity<?> getWarehouseByCustomer(@Valid @PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElse(null);

        if (customer != null) {
            List<WarehouseListResponse> warehouses = new ArrayList<>();

            for (Warehouse w: customer.getWarehouses()) {
                warehouses.add(new WarehouseListResponse(
                        w.getId(),
                        w.getName(),
                        w.getAddress(),
                        w.getPhone()
                ));
            }

            return ResponseEntity.ok(warehouses);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Customer not found!"));
    }
}
