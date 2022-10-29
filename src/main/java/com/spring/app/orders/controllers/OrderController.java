package com.spring.app.orders.controllers;

import com.spring.app.orders.models.repository.OrderItemRepository;
import com.spring.app.orders.models.repository.OrderStatusRepository;
import com.spring.app.orders.payload.request.OrderDataRequest;
import com.spring.app.orders.services.OrderService;
import com.spring.app.payload.MessageResponse;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.repository.OrderRepository;
import com.spring.app.orders.payload.response.OrderListResponse;
import com.spring.app.products.models.repository.PackageRepository;
import com.spring.app.products.models.repository.ProductRepository;
import com.spring.app.shipping.models.repository.ShippingAddressRepository;
import com.spring.app.warehouse.models.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    ProductRepository productRepository;
    @Autowired
    PackageRepository packageRepository;
    @Autowired
    ShippingAddressRepository shippingAddressRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    OrderService orderService;

    private void initOrderService() {
        if (this.orderService == null)
            this.orderService = new OrderService(
                    this.orderRepository,
                    this.orderItemRepository,
                    this.orderStatusRepository,
                    this.customerRepository,
                    this.productRepository,
                    this.packageRepository,
                    this.shippingAddressRepository,
                    this.warehouseRepository
            );
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveOrder(@Valid @RequestBody OrderDataRequest order) {
        this.initOrderService();

        if (order.getCustomerId() != null) {
            this.orderService.placeCustomerOrder(order);
        } else {
            this.orderService.placeGuestOrder(order);
        }

        return ResponseEntity.ok(new MessageResponse("Place order successfully!"));
    }

    @GetMapping("/all/customer/{id}")
    public ResponseEntity<?> getOrderByCustomerId(@Valid @PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElse(null);

        List<OrderListResponse> listOrder = new ArrayList<>();

        if (customer != null) {
            Set<Order> orders = customer.getOrders();

            for (Order o : orders) {
//                OrderListResponse res = new OrderListResponse(
//                        o.getOrderCode(),
//                        o.getStatus().getCode(),
//                        o.getCustomer().getCustomerId(),
//                        o.getNote(),
//                        o.getFeedback(),
//                        o.getSubtotal(),
//                        o.getSenderName(),
//                        o.getSenderPhone(),
//                        o.getSenderAddress()
//                );
//
//                listOrder.add(res);
            }

            return ResponseEntity.ok("test");
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Error: Customer is not found."));
    }
}
