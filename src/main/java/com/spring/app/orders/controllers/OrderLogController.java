package com.spring.app.orders.controllers;

import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.OrderLog;
import com.spring.app.orders.models.repository.OrderRepository;
import com.spring.app.orders.payload.OrderLogData;
import com.spring.app.orders.services.OrderLogService;
import com.spring.app.payload.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/order/history")
public class OrderLogController {
    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    public OrderLogService orderLogService;

    @GetMapping("/orderId/{id}")
    public ResponseEntity<?> getByOrderId(@Valid @PathVariable Long id) {
        Order order = orderRepository.findById(id).orElse(null);

        if (order != null) {
            List<OrderLogData> data = new ArrayList<>();

            for (OrderLog h : order.getHistories()) {
                data.add(orderLogService.processOrderLogResponse(h));
            }

            return ResponseEntity.ok(data);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Order not found!"));
    }
}
