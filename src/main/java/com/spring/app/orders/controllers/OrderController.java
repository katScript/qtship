package com.spring.app.orders.controllers;

import com.spring.app.orders.models.OrderStatus;
import com.spring.app.orders.models.repository.OrderItemRepository;
import com.spring.app.orders.models.repository.OrderStatusRepository;
import com.spring.app.orders.payload.request.OrderDataRequest;
import com.spring.app.orders.payload.request.OrderStatusRequest;
import com.spring.app.orders.payload.request.OrderStatusUpdateRequest;
import com.spring.app.orders.services.OrderService;
import com.spring.app.payload.MessageResponse;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.repository.OrderRepository;
import com.spring.app.orders.payload.response.OrderListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/order")
public class OrderController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrderStatusRepository orderStatusRepository;
    @Autowired
    OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<?> saveOrder(@Valid @RequestBody OrderDataRequest order) {
        if (order.getCustomerId() != null) {
            this.orderService.saveCustomerOrder(order);
        } else {
            this.orderService.saveGuestOrder(order);
        }

        return ResponseEntity.ok(new MessageResponse("Save order successfully!"));
    }

    @PostMapping("/update/status")
    public ResponseEntity<?> updateOrderStatus(@Valid @RequestBody OrderStatusUpdateRequest order) {
        this.orderService.updateStatus(order);

        return ResponseEntity.ok(new MessageResponse("Status update successfully!"));
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@Valid @PathVariable Long id) {
        Order o = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return ResponseEntity.ok(this.orderService.getOrderDetail(o));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrder() {
        List<Order> orders = orderRepository.findAll();
        List<OrderListResponse> listOrder = new ArrayList<>();

        for (Order o : orders) {
            listOrder.add(this.orderService.getOrderDetail(o));
        }

        return ResponseEntity.ok(listOrder);
    }

    @GetMapping("/all/customer/{id}")
    public ResponseEntity<?> getOrderByCustomerId(
            @Valid @PathVariable Long id,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "from", required = false) String from,
            @RequestParam(value = "to", required = false) String to
    ) throws ParseException {
        Customer customer = customerRepository.findById(id)
                .orElse(null);

        List<OrderListResponse> listOrder = new ArrayList<>();

        if (customer != null) {
            List<Order> orders = orderRepository.findByCustomerAndStatusAndCreatedAtBetween(customer, status,
                    from != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(from) : customer.getCreatedAt(),
                    to != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(to) : new Date());

            for (Order o : orders) {
                listOrder.add(this.orderService.getOrderDetail(o));
            }

            return ResponseEntity.ok(listOrder);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Error: Customer is not found."));
    }

    @PostMapping("/status/save")
    public ResponseEntity<?> saveOrderStatus(@Valid @RequestBody OrderStatusRequest orderStatus) {
        OrderStatus status = orderStatusRepository.findByCode(orderStatus.getCode())
                .orElse(new OrderStatus(orderStatus.getCode()));

        Set<OrderStatus> child = new HashSet<>();

        for (String c : orderStatus.getChild()) {
            child.add(orderStatusRepository.findByCode(c)
                    .orElseThrow(() -> new RuntimeException("Can not find order status!")));
        }

        status.setChild(child);
        this.orderStatusRepository.save(status);

        return ResponseEntity.ok(new MessageResponse("Status save successfully!"));
    }
}
