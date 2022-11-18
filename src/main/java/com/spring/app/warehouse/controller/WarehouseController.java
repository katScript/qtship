package com.spring.app.warehouse.controller;

import com.spring.app.payload.CustomPageResponse;
import com.spring.app.payload.FilterRequest;
import com.spring.app.payload.MessageResponse;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.warehouse.models.Warehouse;
import com.spring.app.warehouse.models.repository.WarehouseRepository;
import com.spring.app.customers.payload.request.DeleteRequest;
import com.spring.app.warehouse.payload.WarehouseData;
import com.spring.app.warehouse.payload.request.WarehouseDataRequest;
import com.spring.app.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    WarehouseService warehouseService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllWarehouse(
            @Valid FilterRequest fR
    ) {
        Pageable pageable = PageRequest.of(fR.getPage(), fR.getSize());
        Page<Warehouse> warehouses = warehouseRepository.findAll(pageable);
        List<WarehouseData> warehousesResponse = new ArrayList<>();
        CustomPageResponse pageResponse = new CustomPageResponse(warehouses);

        for (Warehouse w: warehouses) {
            warehousesResponse.add(warehouseService.processWarehouseDataResponse(w));
        }

        pageResponse.setContent(warehousesResponse);
        return ResponseEntity.ok(pageResponse);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@Valid @PathVariable Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find warehouse"));

        return ResponseEntity.ok(warehouseService.processWarehouseDataResponse(warehouse));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveWarehouse(@Valid @RequestBody WarehouseDataRequest warehouseDataRequest) {
        warehouseService.saveWarehouse(warehouseDataRequest);
        return ResponseEntity.ok(new MessageResponse("Warehouse save successfully!"));
    }

    @DeleteMapping("/delete")
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
    public ResponseEntity<?> getWarehouseByCustomer(
            @Valid @PathVariable Long id,
            @Valid FilterRequest fR
    ) {
        Customer customer = customerRepository.findById(id)
                .orElse(null);

        if (customer != null) {
            Pageable pageable = PageRequest.of(fR.getPage(), fR.getSize());
            Page<Warehouse> warehouses = warehouseRepository.findAll(pageable);
            CustomPageResponse pageResponse = new CustomPageResponse(warehouses);
            List<WarehouseData> warehouseData = new ArrayList<>();

            for (Warehouse w: warehouses) {
                warehouseData.add(warehouseService.processWarehouseDataResponse(w));
            }

            pageResponse.setContent(pageable);
            return ResponseEntity.ok(warehouseData);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Customer not found!"));
    }
}
