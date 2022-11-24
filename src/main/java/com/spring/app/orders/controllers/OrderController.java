package com.spring.app.orders.controllers;

import com.spring.app.helper.date.DateFormatHelper;
import com.spring.app.orders.models.repository.OrderStatusRepository;
import com.spring.app.orders.payload.OrderData;
import com.spring.app.orders.payload.request.OrderStatusUpdateRequest;
import com.spring.app.orders.services.OrderService;
import com.spring.app.payload.CustomPageResponse;
import com.spring.app.payload.MessageResponse;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.repository.OrderRepository;
import com.spring.app.payload.OrderFilterRequest;
import com.spring.app.shipping.models.Shipper;
import com.spring.app.shipping.models.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        try {
            for (OrderData order : data) {
                if (order.getCustomerId() != null) {
                    this.orderService.saveCustomerOrder(order);
                } else {
                    this.orderService.saveGuestOrder(order);
                }
            }

            return ResponseEntity.ok(new MessageResponse("Save order successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @PostMapping("/update/status")
    public ResponseEntity<?> updateOrderStatus(@Valid @RequestBody List<OrderStatusUpdateRequest> order) {
        try {
            for (OrderStatusUpdateRequest os : order) {
                this.orderService.updateStatus(os);
            }

            return ResponseEntity.ok(new MessageResponse("Status update successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
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
            @Valid OrderFilterRequest oF
    ) {
        try {
            Long id = oF.getCode() != null ? Long.parseLong(oF.getCode().substring(3)) : null;
            Pageable pageable = PageRequest.of(oF.getPage(), oF.getSize());
            Page<Order> orders = orderRepository.findAllWithFilter(
                    oF.getStatus(),
                    oF.getFrom() != null ? DateFormatHelper.stringToDate(oF.getFrom()) : DateFormatHelper.stringToDate(DateFormatHelper.START_DATE),
                    oF.getTo() != null ? DateFormatHelper.stringToDate(oF.getTo()) : new Date(),
                    oF.getPhone(),
                    DateFormatHelper.stringToDate(oF.getTime()),
                    oF.getType(),
                    id,
                    pageable
            );
            CustomPageResponse pageResponse = new CustomPageResponse(orders);
            List<OrderData> listOrder = new ArrayList<>();

            for (Order o : orders) {
                listOrder.add(this.orderService.getOrderDetail(o));
            }

            pageResponse.setContent(listOrder);
            return ResponseEntity.ok(pageResponse);
        } catch (NumberFormatException e) {
            return ResponseEntity.ok(new CustomPageResponse());
        }
    }

    @GetMapping("/all/page/customer/{id}")
    public ResponseEntity<?> getOrderByCustomerIdPage(
            @Valid @PathVariable Long id,
            @Valid OrderFilterRequest oF
    ) throws ParseException {
        Customer customer = customerRepository.findById(id)
                .orElse(null);

        List<OrderData> listOrder = new ArrayList<>();

        if (customer != null) {
            Pageable pageable = PageRequest.of(oF.getPage(), oF.getSize());
            Page<Order> orders = orderRepository.findByCustomerAndStatusAndCreatedAtBetween(customer, oF.getStatus(),
                    oF.getFrom() != null ? DateFormatHelper.stringToDate(oF.getFrom()) : DateFormatHelper.stringToDate(DateFormatHelper.START_DATE),
                    oF.getTo() != null ? DateFormatHelper.stringToDate(oF.getTo()) : new Date(),
                    pageable
            );
            CustomPageResponse pageResponse = new CustomPageResponse(orders);

            for (Order o : orders) {
                listOrder.add(this.orderService.getOrderDetail(o));
            }

            pageResponse.setContent(listOrder);
            return ResponseEntity.ok(pageResponse);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Error: Customer is not found."));
    }

    @GetMapping("/all/customer/{id}")
    public ResponseEntity<?> getOrderByCustomerId(
            @Valid @PathVariable Long id,
            @Valid OrderFilterRequest oF
    ) throws ParseException {
        Customer customer = customerRepository.findById(id)
                .orElse(null);

        List<OrderData> listOrder = new ArrayList<>();

        if (customer != null) {
            Pageable pageable = Pageable.unpaged();
            Page<Order> orders = orderRepository.findByCustomerAndStatusAndCreatedAtBetween(customer, oF.getStatus(),
                    oF.getFrom() != null ? DateFormatHelper.stringToDate(oF.getFrom()) : DateFormatHelper.stringToDate(DateFormatHelper.START_DATE),
                    oF.getTo() != null ? DateFormatHelper.stringToDate(oF.getTo()) : new Date(),
                    pageable
            );
            CustomPageResponse pageResponse = new CustomPageResponse(orders);

            for (Order o : orders) {
                listOrder.add(this.orderService.getOrderDetail(o));
            }

            pageResponse.setContent(listOrder);
            return ResponseEntity.ok(pageResponse);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Error: Customer is not found."));
    }

    @GetMapping("/all/shipper/{id}")
    public ResponseEntity<?> getOrderByShipperId(
        @Valid @PathVariable Long id,
        @Valid OrderFilterRequest oF
    ) throws ParseException {
        Shipper shipper = shipperRepository.findById(id)
                .orElse(null);

        List<OrderData> listOrder = new ArrayList<>();

        if (shipper != null) {
            Pageable pageable = PageRequest.of(oF.getPage(), oF.getSize());
            Page<Order> orders = orderRepository.findByShipperAndStatusAndCreatedAtBetween(shipper, oF.getStatus(),
                    oF.getFrom() != null ? DateFormatHelper.stringToDate(oF.getFrom()) : DateFormatHelper.stringToDate(DateFormatHelper.START_DATE),
                    oF.getTo() != null ? DateFormatHelper.stringToDate(oF.getTo()) : new Date(),
                    pageable
            );
            CustomPageResponse pageResponse = new CustomPageResponse(orders);

            for (Order o : orders) {
                listOrder.add(this.orderService.getOrderDetail(o));
            }

            pageResponse.setContent(listOrder);
            return ResponseEntity.ok(pageResponse);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Error: Customer is not found."));
    }
}
