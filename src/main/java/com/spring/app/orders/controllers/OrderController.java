package com.spring.app.orders.controllers;

import com.spring.app.orders.models.OrderItem;
import com.spring.app.orders.models.OrderStatus;
import com.spring.app.orders.models.repository.OrderItemRepository;
import com.spring.app.orders.models.repository.OrderStatusRepository;
import com.spring.app.orders.payload.request.OrderDataRequest;
import com.spring.app.orders.payload.request.OrderStatusRequest;
import com.spring.app.orders.payload.response.OrderItemResponse;
import com.spring.app.orders.services.OrderService;
import com.spring.app.payload.MessageResponse;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.repository.OrderRepository;
import com.spring.app.orders.payload.response.OrderListResponse;
import com.spring.app.products.models.Package;
import com.spring.app.products.models.repository.PackageRepository;
import com.spring.app.products.models.repository.ProductRepository;
import com.spring.app.products.payload.response.PackageResponse;
import com.spring.app.shipping.models.repository.ShippingAddressRepository;
import com.spring.app.shipping.payload.response.ShippingAddressResponse;
import com.spring.app.warehouse.models.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
    public ResponseEntity<?> getOrderByCustomerId(
            @Valid @PathVariable Long id,
            @RequestParam(value = "status", required=false, defaultValue = "default") String status,
            @RequestParam(value = "from", required=false) String from,
            @RequestParam(value = "to", required=false) String to
    ) throws ParseException {
        Customer customer = customerRepository.findById(id)
                .orElse(null);

        List<OrderListResponse> listOrder = new ArrayList<>();

        if (customer != null) {
            List<Order> orders = orderRepository.findByCustomerAndStatusAndCreatedAtBetween(customer, status,
                    from != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(from) : customer.getCreatedAt(),
                    to != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(to) : new Date());

            for (Order o : orders) {
                List<OrderItemResponse> orderItemResponses = new ArrayList<>();

                for (OrderItem i : o.getOrderItemSet()) {
                    List<PackageResponse> packageResponses = new ArrayList<>();

                    for (Package itemPackage : i.getPackages()) {
                        packageResponses.add(new PackageResponse(
                                itemPackage.getProduct().getSku(),
                                itemPackage.getQty()
                        ));
                    }

                    orderItemResponses.add(new OrderItemResponse(
                            i.getPrice(),
                            new ShippingAddressResponse(
                                    i.getShippingAddress().getName(),
                                    i.getShippingAddress().getPhone(),
                                    i.getShippingAddress().getProvince(),
                                    i.getShippingAddress().getDistrict(),
                                    i.getShippingAddress().getWard(),
                                    i.getShippingAddress().getProvinceId(),
                                    i.getShippingAddress().getDistrictId(),
                                    i.getShippingAddress().getWardId(),
                                    i.getShippingAddress().getStreet()
                            ),
                            packageResponses
                    ));
                }

                OrderListResponse res = new OrderListResponse(
                        o.getOrderCode(),
                        o.getCustomer().getCustomerId(),
                        o.getStatus(),
                        o.getFeedback(),
                        o.getNote(),
                        o.getSubtotal(),
                        o.getSenderName(),
                        o.getSenderPhone(),
                        o.getSenderAddress(),
                        o.getNotification(),
                        o.getShippingFee(),
                        o.getShippingType(),
                        o.getShippingTime().toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDateTime()
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                        o.getCoupon(),
                        orderItemResponses
                );

                listOrder.add(res);
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
