package com.spring.app.warehouse.service;

import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.helper.date.DateFormatHelper;
import com.spring.app.warehouse.models.Warehouse;
import com.spring.app.warehouse.models.repository.WarehouseRepository;
import com.spring.app.warehouse.payload.WarehouseData;
import com.spring.app.warehouse.payload.request.WarehouseDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CustomerRepository customerRepository;

    public void saveWarehouse(WarehouseDataRequest warehouseRequest) {
        Customer customer = customerRepository.findById(warehouseRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        Warehouse warehouse = processWarehouseData(warehouseRequest.getData());
        warehouse.setCustomer(customer);

        warehouseRepository.save(warehouse);
    }

    public Warehouse processWarehouseData(WarehouseData data) {
        Warehouse warehouse;
        if (data.getId() != null) {
            warehouse = warehouseRepository.findById(data.getId())
                    .orElseThrow(() -> new RuntimeException("Warehouse not found!"));
        } else {
            warehouse = new Warehouse();
        }

        warehouse.setName(data.getName())
                .setPhone(data.getPhone())
                .setAddress(data.getAddress());

        return warehouse;
    }

    public WarehouseData processWarehouseDataResponse(Warehouse warehouse) {
        WarehouseData wh = new WarehouseData(
                warehouse.getId(),
                warehouse.getName(),
                warehouse.getAddress(),
                warehouse.getCustomer().getCustomerId(),
                warehouse.getPhone()
        );

        wh.setCreatedAt(
                DateFormatHelper.dateToString(warehouse.getCreatedAt())
        );
        wh.setUpdatedAt(
                DateFormatHelper.dateToString(warehouse.getUpdatedAt())
        );

        return wh;
    }
}
