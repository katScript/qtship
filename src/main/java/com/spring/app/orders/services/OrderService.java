package com.spring.app.orders.services;

import com.google.gson.Gson;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.helper.date.DateFormatHelper;
import com.spring.app.office.models.Office;
import com.spring.app.office.models.repository.OfficeRepository;
import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.OrderLog;
import com.spring.app.orders.models.OrderStatus;
import com.spring.app.orders.models.repository.OrderRepository;
import com.spring.app.orders.models.repository.OrderStatusRepository;
import com.spring.app.orders.payload.OrderData;
import com.spring.app.orders.payload.request.OrderStatusUpdateRequest;
import com.spring.app.orders.services.dto.*;
import com.spring.app.price.service.PriceCalculate;
import com.spring.app.products.models.Package;
import com.spring.app.products.models.Product;
import com.spring.app.products.payload.PackageData;
import com.spring.app.products.service.ProductService;
import com.spring.app.shipping.models.ShippingAddress;
import com.spring.app.shipping.models.repository.ShippingAddressRepository;
import com.spring.app.shipping.payload.ShipperData;
import com.spring.app.shipping.payload.ShippingAddressData;
import com.spring.app.shipping.service.ShippingService;
import com.spring.app.warehouse.models.Warehouse;
import com.spring.app.warehouse.models.repository.WarehouseRepository;
import com.spring.app.warehouse.payload.WarehouseData;
import com.spring.app.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    @Autowired
    public OrderLogService orderLogService;
    @Autowired
    public OfficeRepository officeRepository;

    public void updateStatus(OrderStatusUpdateRequest order) {
        Order _order = this.orderRepository.findById(order.getId())
                .orElseThrow(() -> new RuntimeException("Order not found!"));

        OrderStatus status = this.orderStatusRepository.findByCode(order.getStatus())
                .orElseThrow(() -> new RuntimeException("Order status not found!"));

        _order.setStatus(status.getCode());
        OrderLog orderLog = orderLogService.createStatusLog(
                _order,
                status.getCode(),
                order.getDescription()
        );

        _order.getHistories().add(orderLog);
        this.orderRepository.save(_order);
    }

    public String saveGuestOrder(OrderData order) {
        Order _order = this.processOrder(order);
        Order orderData = this.orderRepository.save(_order);

        return postOrderToJAndT(orderData);
    }

    public String saveCustomerOrder(OrderData order) {
        Customer customer = this.customerRepository.findById(order.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        Order _order = this.processOrder(order);
        _order.setCustomer(customer);

        Order orderData = this.orderRepository.save(_order);

        return postOrderToJAndT(orderData);
    }

    public String postOrderToJAndT(Order order) {
        String url = "https://test.jtexpress.vn/yuenan-interface-web/order/orderAction!createOrder.action";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        Address sender = Address.builder()
                .name(order.getSenderName())
                .phone(order.getSenderPhone())
                .mobile(order.getSenderPhone())
                .prov(order.getShippingAddress().getProvince())
                .city(order.getShippingAddress().getDistrict())
                .area(order.getShippingAddress().getWard())
                .address(order.getSenderAddress())
                .build();

        Address receiver = Address.builder()
                .name(order.getShippingAddress().getName())
                .phone(order.getShippingAddress().getPhone())
                .mobile(order.getShippingAddress().getPhone())
                .prov(order.getShippingAddress().getProvince())
                .city(order.getShippingAddress().getDistrict())
                .area(order.getShippingAddress().getWard())
                .address(order.getShippingAddress().getStreet())
                .build();

        String currentDate = DateFormatHelper.dateToString(new Date());
        LogisticInterface logisticInterface = LogisticInterface.builder()
                .txlogisticid(order.getOrderCode())
                .sender(sender)
                .receiver(receiver)
                .itemsvalue(String.valueOf(order.getSubtotal()))
                .weight(order.getTotalWeight())
                .createordertime(currentDate)
                .sendstarttime(currentDate)
                .sendendtime(currentDate)
                .remark(order.getReturnCode())
                .items(new ArrayList<>())
                .build();

        for (Package p : order.getPackages()) {
            logisticInterface.getItems().add(
                    Item.builder()
                            .itemname(p.getName())
                            .englishName(p.getName())
                            .number(p.getQty())
                            .itemvalue(null)
                            .build()
            );
        }

        Gson gson = new Gson();

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();

        String key = "04fc653c0f661e1204bd804774e01824";
        String data = gson.toJson(logisticInterface);

        MessageDigest md;
        String myHash;
        try {
            md = MessageDigest.getInstance("MD5");

            String digestData = data + key;

            md.update(digestData.getBytes());
            byte[] digest = md.digest();
            myHash = DatatypeConverter
                    .printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        form.add("logistics_interface", data);
        form.add("data_digest", Base64.getEncoder().encodeToString(myHash.getBytes()));
        form.add("msg_type", "ORDERCREATE");
        form.add("eccompanyid", "CUSMODEL");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);

        return restTemplate.postForObject(url, form, String.class);
    }

    public Order processOrder(OrderData data) {
        Order order;
        OrderStatus status;
        if (data.getId() != null) {
            order = this.orderRepository.findById(data.getId())
                    .orElseThrow(() -> new RuntimeException("Order not found!"));
            status = this.orderStatusRepository.findByCode(data.getStatus() != null ? data.getStatus() : order.getStatus())
                    .orElseThrow(() -> new RuntimeException("Order status not found!"));

            order.getHistories().add(orderLogService.createStatusLog(
                    order,
                    status.getCode(),
                    "Update order."
            ));
        } else {
            order = new Order();
            status = this.orderStatusRepository.findByCode(OrderStatusService.DEFAULT)
                    .orElseThrow(() -> new RuntimeException("Order status not found!"));

            order.getHistories().add(orderLogService.createStatusLog(
                    order,
                    status.getCode(),
                    "Create order."
            ));
        }

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

        this.processOffice(data, order);
        this.processWarehouse(data, order);
        this.processPackage(data.getProducts(), order);
        this.processOrderWeight(order);
        this.priceCalculate.processSubtotal(order);

        return order;
    }

    public void processOffice(OrderData data, Order order) {
        if (data.getOfficeId() != null) {
            Office office = officeRepository.findById(data.getOfficeId()).orElseThrow(() -> new RuntimeException("Office not found!"));
            order.setOffice(office);
        }
    }

    public void processWarehouse(OrderData data, Order order) {
        if (data.getWarehouse() == null || data.getWarehouse().getId() == null) {
            order.setTakenTime(null).setWarehouse(null);
        } else {
            Warehouse warehouse = this.warehouseRepository.findById(data.getWarehouse().getId())
                    .orElseThrow(() -> new RuntimeException("Warehouse not found!"));

            order.setTakenTime(
                    DateFormatHelper.stringToDate(data.getShippingTime())
            ).setWarehouse(
                    warehouse
            ).setOffice(
                    officeRepository.findFirstByProvinceId(warehouse.getProvinceId())
                            .orElse(null)
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

            packageItems.setOrder(order);
            packages.add(packageItems);
        }

        order.getPackages().clear();
        order.getPackages().addAll(packages);
    }

    public OrderData getOrderDetail(Order order) {
        Customer customer = order.getCustomer();
        WarehouseData warehouseData = order.getWarehouse() == null ?
                new WarehouseData() : warehouseService.processWarehouseDataResponse(order.getWarehouse());
        ShipperData shipperData = order.getShipper() == null ?
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
                DateFormatHelper.dateToString(order.getTakenTime()),
                DateFormatHelper.dateToString(order.getShippingTime()),
                order.getReturnCode(),
                products,
                order.getOffice() == null ? null : order.getOffice().getId()
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
