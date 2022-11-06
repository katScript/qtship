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
import com.spring.app.orders.payload.request.OrderStatusRequest;
import com.spring.app.orders.payload.request.OrderStatusUpdateRequest;
import com.spring.app.orders.payload.response.OrderItemResponse;
import com.spring.app.orders.payload.response.OrderListResponse;
import com.spring.app.price.models.repository.CouponRepository;
import com.spring.app.price.service.PriceCalculate;
import com.spring.app.products.models.Package;
import com.spring.app.products.models.Product;
import com.spring.app.products.models.repository.PackageRepository;
import com.spring.app.products.models.repository.ProductRepository;
import com.spring.app.products.payload.request.PackageDataRequest;
import com.spring.app.products.payload.response.ProductDetailResponse;
import com.spring.app.products.service.ProductService;
import com.spring.app.shipping.models.ShippingAddress;
import com.spring.app.shipping.models.repository.ShippingAddressRepository;
import com.spring.app.shipping.payload.request.ShippingAddressRequest;
import com.spring.app.shipping.payload.response.ShippingAddressResponse;
import com.spring.app.warehouse.models.repository.WarehouseRepository;
import com.spring.app.warehouse.payload.response.WarehouseListResponse;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
    public PriceCalculate priceCalculate;
    public ProductService productService;

    public OrderService() {}

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            OrderStatusRepository orderStatusRepository,
            CustomerRepository customerRepository,
            ProductRepository productRepository,
            PackageRepository packageRepository,
            ShippingAddressRepository shippingAddressRepository,
            WarehouseRepository warehouseRepository,
            CouponRepository couponRepository
    ) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderStatusRepository = orderStatusRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.packageRepository = packageRepository;
        this.shippingAddressRepository = shippingAddressRepository;
        this.warehouseRepository = warehouseRepository;
        this.productService = new ProductService();
        this.priceCalculate = new PriceCalculate(couponRepository);

    }

    public void updateStatus(OrderStatusUpdateRequest order) {
        Order _order = this.orderRepository.findById(order.getId())
                .orElseThrow(() -> new RuntimeException("Order not found!"));


        OrderStatus status = this.orderStatusRepository.findByCode(order.getStatus())
                .orElseThrow(() -> new RuntimeException("Order status not found!"));

        _order.setStatus(status.getCode());
        this.orderRepository.save(_order);
    }

    public void saveGuestOrder(OrderDataRequest order) {
        Order _order = this.processOrder(order);
        this.orderRepository.save(_order);
    }

    public void saveCustomerOrder(OrderDataRequest order) {
        Customer customer = this.customerRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        Order _order = this.processOrder(order);
        _order.setCustomer(customer);

        this.orderRepository.save(_order);
    }

    public Order processOrder(OrderDataRequest order) {
        Order _order;
        if (order.getId() != null) {
            _order = this.orderRepository.findById(order.getId())
                    .orElseThrow(() -> new RuntimeException("Order not found!"));
        } else {
            _order = new Order();
        }

        _order.setCoupon(order.getCoupon())
                .setFeedback(order.getFeedback())
                .setNote(order.getNote())
                .setNotification(order.getNotification())
                .setSenderName(order.getSenderName())
                .setSenderPhone(order.getSenderName())
                .setSenderPhone(order.getSenderPhone())
                .setSenderAddress(order.getSenderAddress())
                .setShippingFee(order.getShippingFee())
                .setShippingType(order.getShippingType())
                .setReturnCode(order.getReturnCode())
                .setShippingTime(Date.from(order.getShippingDate().atZone(ZoneId.systemDefault()).toInstant()))
                .setWarehouse(
                        this.warehouseRepository.findById(order.getWarehouseId())
                                .orElseThrow(() -> new RuntimeException("Warehouse not found!"))
                );

        Set<OrderItem> orderItems = this.processOrderItem(order.getOrderItem(), _order);
        _order.setOrderItemSet(orderItems);
        priceCalculate.processSubtotal(_order);

        OrderStatus status = this.orderStatusRepository.findByCode(order.getStatus()).orElseThrow(() -> new RuntimeException("Order status not found!"));
        _order.setStatus(status.getCode());

        return _order;
    }

    public Set<OrderItem> processOrderItem(List<OrderItemRequest> orderItems, Order order) {
        Set<OrderItem> orderItemSet = new HashSet<>();

        for (OrderItemRequest od: orderItems) {
            OrderItem orderItem;

            if (od.getId() != null) {
                orderItem = this.orderItemRepository.findById(od.getId())
                        .orElseThrow(() -> new RuntimeException("Process order error!"));
            } else {
                orderItem = new OrderItem();
            }

            Set<Package> packageSet = this.processPackage(od.getProducts(), orderItem);

            orderItem.setPackages(packageSet);
            orderItem.setShippingAddress(this.processShippingAddress(od.getShippingAddress()));
            orderItem.setOrder(order);
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

        _shippingAddress.setName(shippingAddress.getName())
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

    public Set<Package> processPackage(List<PackageDataRequest> products, OrderItem orderItem) {
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
            pkage.setOrderItem(orderItem);

            packages.add(pkage);
        }

        return packages;
    }

    public OrderListResponse getOrderDetail(Order order) {
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();

        for (OrderItem i : order.getOrderItemSet()) {
            List<ProductDetailResponse> productDetailResponses = new ArrayList<>();

            for (Package itemPackage : i.getPackages()) {
                productDetailResponses.add(
                        this.productService.
                                processProductDataResponse(itemPackage.getProduct())
                );
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
                    productDetailResponses
            ));
        }

        return new OrderListResponse(
                order.getOrderCode(),
                order.getCustomer().getCustomerId(),
                order.getStatus(),
                order.getFeedback(),
                order.getNote(),
                order.getSubtotal(),
                order.getSenderName(),
                order.getSenderPhone(),
                order.getSenderAddress(),
                order.getNotification(),
                order.getShippingFee(),
                order.getShippingType(),
                order.getShippingTime().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                order.getCoupon(),
                order.getCreatedAt().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                order.getUpdatedAt().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                orderItemResponses,
                new WarehouseListResponse(
                        order.getWarehouse().getId(),
                        order.getWarehouse().getName(),
                        order.getWarehouse().getAddress(),
                        order.getWarehouse().getPhone()
                ),
                order.getReturnCode()
        );
    }
}
