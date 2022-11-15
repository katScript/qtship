package com.spring.app.orders.services;

import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.helper.services.DateFormatHelper;
import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.OrderItem;
import com.spring.app.orders.models.OrderStatus;
import com.spring.app.orders.models.repository.OrderItemRepository;
import com.spring.app.orders.models.repository.OrderRepository;
import com.spring.app.orders.models.repository.OrderStatusRepository;
import com.spring.app.orders.payload.OrderData;
import com.spring.app.orders.payload.OrderItemData;
import com.spring.app.orders.payload.request.OrderStatusUpdateRequest;
import com.spring.app.price.service.PriceCalculate;
import com.spring.app.products.models.Package;
import com.spring.app.products.models.Product;
import com.spring.app.products.models.repository.PackageRepository;
import com.spring.app.products.models.repository.ProductRepository;
import com.spring.app.products.payload.PackageData;
import com.spring.app.products.payload.ProductData;
import com.spring.app.products.service.ProductService;
import com.spring.app.shipping.models.ShippingAddress;
import com.spring.app.shipping.models.repository.ShippingAddressRepository;
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
    public OrderItemRepository orderItemRepository;
    @Autowired
    public OrderStatusRepository orderStatusRepository;
    @Autowired
    public CustomerRepository customerRepository;
    @Autowired
    public PackageRepository packageRepository;
    @Autowired
    public ProductRepository productRepository;
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
        Order _order;
        if (data.getId() != null) {
            _order = this.orderRepository.findById(data.getId())
                    .orElseThrow(() -> new RuntimeException("Order not found!"));
        } else {
            _order = new Order();
        }

        _order.setCoupon(data.getCoupon())
                .setFeedback(data.getFeedback())
                .setNote(data.getNote())
                .setNotification(data.getNotification())
                .setSenderName(data.getSenderName())
                .setSenderPhone(data.getSenderName())
                .setSenderPhone(data.getSenderPhone())
                .setSenderAddress(data.getSenderAddress())
                .setShippingFee(data.getShippingFee())
                .setShippingType(data.getShippingType())
                .setReturnCode(data.getReturnCode());

        if (data.getWarehouse().getId() != null) {
            _order.setShippingTime(
                    DateFormatHelper.stringToDate(data.getShippingTime())
            ).setWarehouse(
                    this.warehouseRepository.findById(data.getWarehouse().getId())
                            .orElseThrow(() -> new RuntimeException("Warehouse not found!"))
            );
        } else {
            _order.setShippingTime(null).setWarehouse(null);
        }

        Set<OrderItem> orderItems = this.processOrderItem(data.getOrderItem(), _order);

        _order.getOrderItemSet().clear();
        _order.getOrderItemSet().addAll(orderItems);
        this.processOrderWeight(_order);
        priceCalculate.processSubtotal(_order);

        OrderStatus status = this.orderStatusRepository.findByCode(data.getStatus()).orElseThrow(() -> new RuntimeException("Order status not found!"));
        _order.setStatus(status.getCode());

        return _order;
    }

    public Set<OrderItem> processOrderItem(List<OrderItemData> orderItems, Order order) {
        Set<OrderItem> orderItemSet = new HashSet<>();

        for (OrderItemData od: orderItems) {
            OrderItem orderItem;

            if (od.getId() != null) {
                orderItem = this.orderItemRepository.findById(od.getId())
                        .orElseThrow(() -> new RuntimeException("Process order error!"));
            } else {
                orderItem = new OrderItem();
                orderItem.setOrder(order);
            }

            Set<Package> packageSet = this.processPackage(od.getProducts(), orderItem);

            orderItem.getPackages().clear();
            orderItem.getPackages().addAll(packageSet);

            orderItem.setShippingAddress(this.processShippingAddress(od.getShippingAddress()));
            orderItemSet.add(orderItem);
        }

        return orderItemSet;
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

    public Set<Package> processPackage(List<PackageData> products, OrderItem orderItem) {
        Set<Package> packages = new HashSet<>();
        HashMap<Long, Boolean> map = new HashMap<>();

        for (PackageData p: products) {
            ProductData productData = p.getProduct();

            if (map.get(productData.getId()) != null)
                throw new RuntimeException("Not valid Product principle!");

            map.put(productData.getId(), true);
            Product product = this.productRepository.findById(productData.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found!"));

            Package pkage;
            Integer curQty = product.getQty();

            if (p.getId() != null) {
                pkage = this.packageRepository.findById(p.getId())
                        .orElseThrow(() -> new RuntimeException("Process package error!"));

                curQty += pkage.getQty();
            } else {
                pkage = new Package();
            }

            if (curQty > 0 && curQty < p.getQty())
                throw new RuntimeException("Not enough quantity!");

            product.setQty(curQty - p.getQty());
            pkage.setQty(p.getQty())
                    .setOrderItem(orderItem)
                    .setProduct(product);

            packages.add(pkage);
        }

        return packages;
    }

    public OrderData getOrderDetail(Order order) {
        List<OrderItemData> orderItemResponses = new ArrayList<>();

        for (OrderItem i : order.getOrderItemSet()) {
            List<PackageData> productDetailResponses = new ArrayList<>();

            for (Package itemPackage : i.getPackages()) {
                PackageData pD = this.productService.
                        processPackageProductResponse(itemPackage);
                
                productDetailResponses.add(pD);
            }

            orderItemResponses.add(new OrderItemData(
                    i.getId(),
                    i.getPrice(),
                    shippingService.processShippingAddressResponse(i.getShippingAddress()),
                    productDetailResponses
            ));
        }

        OrderData orderData = new OrderData(
                order.getId(),
                order.getOrderCode(),
                order.getCustomer().getId(),
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
                DateFormatHelper.dateToString(order.getShippingTime()),
                order.getCoupon(),
                order.getWarehouse() == null ? new WarehouseData() : warehouseService.processWarehouseDataResponse(order.getWarehouse()),
                order.getReturnCode(),
                orderItemResponses,
                order.getTotalWeight()
        );

        orderData.setCreatedAt(
                DateFormatHelper.dateToString(order.getCreatedAt())
        );
        orderData.setUpdateAt(
                DateFormatHelper.dateToString(order.getUpdatedAt())
        );

        return orderData;
    }

    private void processOrderWeight(Order order) {
        Double weight = 0.0;

        for (OrderItem item: order.getOrderItemSet()) {
            for (Package p: item.getPackages()) {
                weight += p.getProduct().getWeight();
            }
        }

        order.setTotalWeight(weight);
    }
}
