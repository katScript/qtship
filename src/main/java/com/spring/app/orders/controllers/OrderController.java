package com.spring.app.orders.controllers;

import com.spring.app.helper.services.DateFormatHelper;
import com.spring.app.orders.models.OrderStatus;
import com.spring.app.orders.models.repository.OrderStatusRepository;
import com.spring.app.orders.payload.OrderData;
import com.spring.app.orders.payload.OrderStatusData;
import com.spring.app.orders.payload.request.OrderStatusUpdateRequest;
import com.spring.app.orders.services.OrderService;
import com.spring.app.payload.MessageResponse;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.repository.OrderRepository;
import com.spring.app.shipping.models.Shipper;
import com.spring.app.shipping.models.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
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
    OrderStatusRepository orderStatusRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    ShipperRepository shipperRepository;

    @PostMapping("/save")
    public ResponseEntity<?> saveOrder(@Valid @RequestBody List<OrderData> data) {
        for (OrderData order : data) {
            if (order.getCustomerId() != null) {
                this.orderService.saveCustomerOrder(order);
            } else {
                this.orderService.saveGuestOrder(order);
            }
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

    @GetMapping("/customer/{customerId}/detail/{id}")
    public ResponseEntity<?> getById(@Valid @PathVariable Long customerId,  @PathVariable Long id) {
        Customer c = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not exists!"));
        Order o = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

        if (!c.getId().equals(o.getCustomer().getId()))
            return ResponseEntity.badRequest().body("Customer not valid!");

        return ResponseEntity.ok(this.orderService.getOrderDetail(o));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrder(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "from", required = false) String from,
            @RequestParam(value = "to", required = false) String to
    ) {
        List<Order> orders = orderRepository.findAllByStatusAndCreatedAtBetween(
                status,
                from != null ? DateFormatHelper.stringToDate(from) : DateFormatHelper.stringToDate(DateFormatHelper.START_DATE),
                to != null ? DateFormatHelper.stringToDate(to) : new Date()
        );

        List<OrderData> listOrder = new ArrayList<>();

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

        List<OrderData> listOrder = new ArrayList<>();

        if (customer != null) {
            List<Order> orders = orderRepository.findByCustomerAndStatusAndCreatedAtBetween(customer, status,
                    from != null ? DateFormatHelper.stringToDate(from) : DateFormatHelper.stringToDate(DateFormatHelper.START_DATE),
                    to != null ? DateFormatHelper.stringToDate(to) : new Date());

            for (Order o : orders) {
                listOrder.add(this.orderService.getOrderDetail(o));
            }

            return ResponseEntity.ok(listOrder);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Error: Customer is not found."));
    }

    @GetMapping("/all/shipper/{id}")
    public ResponseEntity<?> getOrderByShipperId(
        @Valid @PathVariable Long id,
        @RequestParam(value = "status", required = false) String status,
        @RequestParam(value = "from", required = false) String from,
        @RequestParam(value = "to", required = false) String to
    ) throws ParseException {
        Shipper shipper = shipperRepository.findById(id)
                .orElse(null);

        List<OrderData> listOrder = new ArrayList<>();

        if (shipper != null) {
            List<Order> orders = orderRepository.findByShipperAndStatusAndCreatedAtBetween(shipper, status,
                    from != null ? DateFormatHelper.stringToDate(from) : DateFormatHelper.stringToDate(DateFormatHelper.START_DATE),
                    to != null ? DateFormatHelper.stringToDate(to) : new Date());

            for (Order o : orders) {
                listOrder.add(this.orderService.getOrderDetail(o));
            }

            return ResponseEntity.ok(listOrder);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Error: Customer is not found."));
    }

    @PostMapping("/status/save")
    public ResponseEntity<?> saveOrderStatus(@Valid @RequestBody OrderStatusData orderStatus) {
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
