package com.spring.app.orders.controllers;

import com.spring.app.authentication.payload.response.MessageResponse;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.repository.OrderRepository;
import com.spring.app.orders.payload.response.OrderListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/order")
public class OrderController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getOrderByCustomerId(@Valid @PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElse(null);

        if (customer != null) {
            List<Order> orders = new ArrayList<>();
            List<OrderListResponse> responses = new ArrayList<>();

            for (Order o: orders) {
                responses.add(new OrderListResponse(
                        o.getOrderCode(),
                        o.getStatus().getCode(),
                        o.getCustomer() == null ? null : o.getCustomer().getCustomerId(),
                        o.getNote(),
                        o.getFeedback(),
                        o.getSubtotal(),
                        o.getCustomer() == null ? o.getSenderName() : o.getCustomer().getFullName(),
                        o.getCustomer() == null ? o.getSenderPhone() : o.getCustomer().getPhone(),
                        o.getCustomer() == null ? o.getSenderAddress() : o.getCustomer().getFullName()
                ));
            }
        }

        return ResponseEntity.ok(new MessageResponse("Error: Customer is not found."));
    }

    @GetMapping("/customer/{id}/detail")
    public ResponseEntity<?> getDetailOrderByCustomerId(@Valid @PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElse(null);

        if (customer != null) {
            List<Order> orders = new ArrayList<>();

            for (Order o: orders) {

            }
        }

        return ResponseEntity.ok(new MessageResponse("Error: Customer is not found."));
    }
}
