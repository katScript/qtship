package com.spring.app.orders.services;

import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.OrderItem;
import com.spring.app.orders.models.OrderStatus;
import com.spring.app.orders.models.repository.OrderItemRepository;
import com.spring.app.orders.models.repository.OrderRepository;
import com.spring.app.orders.models.repository.OrderStatusRepository;
import com.spring.app.orders.payload.request.OrderDataRequest;
import com.spring.app.orders.payload.request.OrderItemRequest;
import com.spring.app.products.models.Package;
import com.spring.app.products.models.Product;
import com.spring.app.products.models.repository.PackageRepository;
import com.spring.app.products.models.repository.ProductRepository;
import com.spring.app.products.payload.request.PackageDataRequest;
import com.spring.app.shipping.models.ShippingAddress;
import com.spring.app.shipping.models.repository.ShippingAddressRepository;
import com.spring.app.shipping.payload.request.ShippingAddressRequest;
import com.spring.app.warehouse.models.Warehouse;
import com.spring.app.warehouse.models.repository.WarehouseRepository;
import com.spring.app.warehouse.payload.request.WarehouseDataRequest;

import java.util.*;

public class OrderService {
    public OrderRepository orderRepository;
    public OrderItemRepository orderItemRepository;
    public OrderStatusRepository orderStatusRepository;
    public CustomerRepository customerRepository;
    public PackageRepository packageRepository;
    public ProductRepository productRepository;
    public ShippingAddressRepository shippingAddressRepository;
    public WarehouseRepository warehouseRepository;

    public OrderService() {}

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            OrderStatusRepository orderStatusRepository,
            CustomerRepository customerRepository,
            ProductRepository productRepository,
            PackageRepository packageRepository,
            ShippingAddressRepository shippingAddressRepository,
            WarehouseRepository warehouseRepository
    ) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.packageRepository = packageRepository;
        this.shippingAddressRepository = shippingAddressRepository;
        this.warehouseRepository = warehouseRepository;

    }

    public void placeGuestOrder(OrderDataRequest order) {
        Order _order;
        if (order.getId() != null) {
            _order = this.orderRepository.findById(order.getId())
                    .orElseThrow(() -> new RuntimeException("Order not found!"));
        } else {
            _order = new Order();
        }

        Set<OrderItem> orderItems = processOrder(order);
        Double subtotal = 0.0;

        for (OrderItem oi: orderItems) {
            subtotal += oi.getPrice();
        }

        _order.setCoupon(order.getCoupon())
                .setFeedback(order.getFeedback())
                .setNote(order.getNote())
                .setNotification(order.getNotification())
                .setSenderName(order.getSenderName())
                .setSenderPhone(order.getSenderName())
                .setSenderPhone(order.getSenderPhone())
                .setShippingFee(order.getShippingFee())
                .setShippingType(order.getShippingType())
                .setOrderItemSet(orderItems)
                .setSubtotal(subtotal)
                .setStatus(this.orderStatusRepository.findByCode(order.getStatus())
                        .orElseThrow(() -> new RuntimeException("Order status not found!"))
                );

        this.orderRepository.save(_order);
    }

    public void placeCustomerOrder(OrderDataRequest order) {
        Customer customer = this.customerRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        Order _order;
        if (order.getId() != null) {
            _order = this.orderRepository.findById(order.getId())
                    .orElseThrow(() -> new RuntimeException("Order not found!"));
        } else {
            _order = new Order();
        }

        Set<OrderItem> orderItems = processOrder(order);
        Double subtotal = 0.0;

        for (OrderItem oi: orderItems) {
            subtotal += oi.getPrice();
        }

        _order.setCustomer(customer)
                .setCoupon(order.getCoupon())
                .setFeedback(order.getFeedback())
                .setNote(order.getNote())
                .setNotification(order.getNotification())
                .setSenderName(order.getSenderName())
                .setSenderPhone(order.getSenderName())
                .setSenderPhone(order.getSenderPhone())
                .setShippingFee(order.getShippingFee())
                .setShippingType(order.getShippingType())
                .setWarehouse(
                        this.warehouseRepository.findById(order.getWarehouseId())
                                .orElseThrow(() -> new RuntimeException("Warehouse not found!"))
                ).setOrderItemSet(orderItems)
                .setSubtotal(subtotal)
                .setStatus(this.orderStatusRepository.findByCode(order.getStatus())
                    .orElseThrow(() -> new RuntimeException("Order status not found!"))
                );

        this.orderRepository.save(_order);
    }

//    public OrderStatus processStatus(String curStatus, String status) {
//        OrderStatus curOrderStatus;
//        if (curStatus != null)
//            curOrderStatus = this.orderStatusRepository.findByCode(curStatus)
//                .orElseThrow(() -> new RuntimeException("Current status not found!"));
//        else
//
//        OrderStatus orderStatus = this.orderStatusRepository.findByCode(status)
//                .orElseThrow(() -> new RuntimeException("Order status not found!"));
//
//
//    }

    public Set<OrderItem> processOrder(OrderDataRequest order) {
        List<OrderItemRequest> orderItems = order.getOrderItem();
        Set<OrderItem> orderItemSet = new HashSet<>();

        for (OrderItemRequest od: orderItems) {
            OrderItem orderItem;

            if (od.getId() != null) {
                orderItem = this.orderItemRepository.findById(od.getId())
                        .orElseThrow(() -> new RuntimeException("Process order error!"));
            } else {
                orderItem = new OrderItem();
            }

            orderItem.setPackages(this.processPackage(od.getProducts()));
            orderItem.setShippingAddress(this.processShippingAddress(od.getShippingAddress()));
            orderItem.setPrice(processPrice(orderItem.getPackages()));

            orderItemSet.add(orderItem);
        }

        return orderItemSet;
    }

    public Double processPrice(Set<Package> packages) {
        double price = 0.0;

        for (Package p: packages)
            price += p.getQty() * p.getProduct().getPublicPrice();

        return price;
    }

    public ShippingAddress processShippingAddress(ShippingAddressRequest shippingAddress) {
        ShippingAddress _shippingAddress;

        if (shippingAddress.getId() != null) {
            _shippingAddress = this.shippingAddressRepository.findById(shippingAddress.getId())
                    .orElseThrow(() -> new RuntimeException("Shipping address not found!"));
        } else {
            _shippingAddress = new ShippingAddress();
        }

        _shippingAddress.setName(shippingAddress.getReceiverName())
                .setPhone(shippingAddress.getPhone())
                .setProvince(shippingAddress.getProvince())
                .setProvinceId(shippingAddress.getProvinceId())
                .setDistrict(shippingAddress.getDistrict())
                .setDistrictId(shippingAddress.getDistrictId())
                .setWard(shippingAddress.getWard())
                .setWardId(shippingAddress.getWardId())
                .setStreet(shippingAddress.getStreet());

        return _shippingAddress;
    }

    public Set<Package> processPackage(List<PackageDataRequest> products) {
        Set<Package> packages = new HashSet<>();
        HashMap<Long, Boolean> map = new HashMap<>();

        for (PackageDataRequest p: products) {
            if (map.get(p.getProductId()) != null)
                throw new RuntimeException("Not valid Product principle!");

            map.put(p.getProductId(), true);
            Product product = this.productRepository.findById(p.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found!"));

            Package pkage;
            Integer curQty = product.getQty();

            if (p.getId() != null) {
                pkage = this.packageRepository.findById(p.getId())
                        .orElseThrow(() -> new RuntimeException("Process package error!"));

                curQty += pkage.getQty();
            } else {
                pkage = new Package();
                pkage.setProduct(product);
            }

            if (curQty > 0 && curQty < p.getQty())
                throw new RuntimeException("Not enough quantity!");

            product.setQty(curQty - p.getQty());
            pkage.setQty(p.getQty());
            packages.add(pkage);
        }

        return packages;
    }
}
