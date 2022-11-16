package com.spring.app.orders.services;

import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.helper.services.DateFormatHelper;
import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.OrderStatus;
import com.spring.app.orders.models.repository.OrderRepository;
import com.spring.app.orders.models.repository.OrderStatusRepository;
import com.spring.app.orders.payload.OrderData;
import com.spring.app.orders.payload.request.OrderStatusUpdateRequest;
import com.spring.app.price.service.PriceCalculate;
import com.spring.app.products.models.Package;
import com.spring.app.products.payload.PackageData;
import com.spring.app.products.service.ProductService;
import com.spring.app.shipping.models.ShippingAddress;
import com.spring.app.shipping.models.repository.ShippingAddressRepository;
import com.spring.app.shipping.payload.ShipperData;
import com.spring.app.shipping.payload.ShippingAddressData;
import com.spring.app.shipping.service.ShippingService;
import com.spring.app.warehouse.models.repository.WarehouseRepository;
import com.spring.app.warehouse.payload.WarehouseData;
import com.spring.app.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    public OrderStatusRepository orderStatusRepository;
    @Autowired
    public CustomerRepository customerRepository;
    @Autowired
    public ShippingAddressRepository shippingAddressRepository;
    @Autowired
    public WarehouseRepository warehouseRepository;
    @Autowired
    public PriceCalculate priceCalculate;
    @Autowired
    public ProductService productService;
    @Autowired
    public WarehouseService warehouseService;
    @Autowired
    public ShippingService shippingService;

    public void updateStatus(OrderStatusUpdateRequest order) {
        Order _order = this.orderRepository.findById(order.getId())
                .orElseThrow(() -> new RuntimeException("Order not found!"));


        OrderStatus status = this.orderStatusRepository.findByCode(order.getStatus())
                .orElseThrow(() -> new RuntimeException("Order status not found!"));

        _order.setStatus(status.getCode());
        this.orderRepository.save(_order);
    }

    public void saveGuestOrder(OrderData order) {
        Order _order = this.processOrder(order);
        this.orderRepository.save(_order);
    }

    public void saveCustomerOrder(OrderData order) {
        Customer customer = this.customerRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        Order _order = this.processOrder(order);
        _order.setCustomer(customer);

        this.orderRepository.save(_order);
    }

    public Order processOrder(OrderData data) {
        Order order;
        if (data.getId() != null) {
            order = this.orderRepository.findById(data.getId())
                    .orElseThrow(() -> new RuntimeException("Order not found!"));
        } else {
            order = new Order();
        }

        OrderStatus status = this.orderStatusRepository.findByCode(data.getStatus()).orElseThrow(() -> new RuntimeException("Order status not found!"));

        order.setSenderName(data.getSenderName())
                .setSenderPhone(data.getSenderPhone())
                .setSenderAddress(data.getSenderAddress())
                .setNote(data.getNote())
                .setFeedback(data.getFeedback())
                .setNotification(data.getNotification())
                .setShipPayer(data.getShipPayer())
                .setCoupon(data.getCoupon())
                .setShippingType(data.getShippingType())
                .setReturnCode(data.getReturnCode())
                .setShippingAddress(
                        this.processShippingAddress(data.getShippingAddress())
                ).setStatus(status.getCode());

        this.processWarehouse(data, order);
        this.processPackage(data.getProducts(), order);
        this.processOrderWeight(order);
        this.priceCalculate.processSubtotal(order);

        return order;
    }

    public void processWarehouse(OrderData data, Order order) {
        if (data.getId() == null) {
            order.setShippingTime(null).setWarehouse(null);
        } else {
            order.setShippingTime(
                    DateFormatHelper.stringToDate(data.getShippingTime())
            ).setWarehouse(
                    this.warehouseRepository.findById(data.getWarehouse().getId())
                            .orElseThrow(() -> new RuntimeException("Warehouse not found!"))
            );
        }
    }

    public ShippingAddress processShippingAddress(ShippingAddressData shippingAddress) {
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

    public void processPackage(Set<PackageData> products, Order order) {
        Set<Package> packages = new HashSet<>();

        for (PackageData p: products) {
            Package packageItems = productService.processPackage(p);

            if (packageItems.getProduct() != null) {
                Integer curQty = packageItems.getProduct().getQty();

                if (curQty > 0 && curQty < p.getQty())
                    throw new RuntimeException("Not enough quantity!");

                packageItems.getProduct().setQty(curQty - p.getQty());

            }
            packages.add(packageItems);
        }

        order.getPackages().clear();
        order.getPackages().addAll(packages);
    }

    public OrderData getOrderDetail(Order order) {
        Customer customer = order.getCustomer();
        WarehouseData warehouseData = order.getWarehouse().getId() == null ?
                new WarehouseData() : warehouseService.processWarehouseDataResponse(order.getWarehouse());
        ShipperData shipperData = order.getShipper().getId() == null ?
                new ShipperData() : shippingService.processShipperDataResponse(order.getShipper());

        Set<PackageData> products = new HashSet<>();

        for (Package item : order.getPackages()) {
            products.add(productService.processPackageProductResponse(item));
        }

        OrderData orderData = new OrderData(
                order.getId(),
                order.getOrderCode(),
                customer == null ? null : customer.getId(),
                customer == null ? null : customer.getCustomerId(),
                order.getSenderName(),
                order.getSenderPhone(),
                order.getSenderAddress(),
                order.getSubtotal(),
                order.getTotalWeight(),
                order.getNote(),
                order.getStatus(),
                order.getFeedback(),
                order.getNotification(),
                order.getShipPayer(),
                order.getShippingFee(),
                order.getCoupon(),
                warehouseData,
                shipperData,
                shippingService.processShippingAddressResponse(order.getShippingAddress()),
                order.getShippingType(),
                DateFormatHelper.dateToString(order.getShippingTime()),
                order.getReturnCode(),
                products
        );

        orderData.setCreatedAt(
                DateFormatHelper.dateToString(order.getCreatedAt())
        );
        orderData.setUpdatedAt(
                DateFormatHelper.dateToString(order.getUpdatedAt())
        );

        return orderData;
    }

    private void processOrderWeight(Order order) {
        Double weight = 0.0;

        for (Package item: order.getPackages()) {
                weight += item.getWeight() * item.getQty();
        }

        order.setTotalWeight(weight);
    }
}
