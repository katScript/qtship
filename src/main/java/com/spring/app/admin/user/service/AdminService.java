package com.spring.app.admin.user.service;


import com.spring.app.admin.user.models.Admin;
import com.spring.app.admin.user.models.repository.AdminRepository;
import com.spring.app.admin.user.payload.AdminData;
import com.spring.app.admin.user.payload.request.AssignOrderRequest;
import com.spring.app.authentication.models.User;
import com.spring.app.helper.date.DateFormatHelper;
import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.OrderLog;
import com.spring.app.orders.models.repository.OrderRepository;
import com.spring.app.orders.services.OrderLogService;
import com.spring.app.orders.services.OrderStatusService;
import com.spring.app.shipping.models.Shipper;
import com.spring.app.shipping.models.ShipperOrder;
import com.spring.app.shipping.models.repository.ShipperOrderRepository;
import com.spring.app.shipping.models.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ShipperRepository shipperRepository;
    @Autowired
    ShipperOrderRepository shipperOrderRepository;
    @Autowired
    OrderLogService orderLogService;

    public void createUser(User user, AdminData data) {
        Admin admin = processAdminData(data);
        admin.setUser(user);

        adminRepository.save(admin);
    }

    public Admin processAdminData(AdminData data) {
        Admin admin;

        if (data.getId() == null) {
            admin = new Admin();
        } else {
            admin = adminRepository.findById(data.getId())
                    .orElseThrow(() -> new RuntimeException("Admin not found"));
        }

        admin.setAddress(data.getAddress())
                .setCurrentAddress(data.getCurrentAddress())
                .setFullName(data.getFullName())
                .setPhone(data.getPhone());

        return admin;
    }

    public AdminData processAdminResponse(Admin admin) {
        AdminData data = new AdminData(
                admin.getId(),
                admin.getUser().getUsername(),
                admin.getUser().getId(),
                admin.getFullName(),
                admin.getPhone(),
                admin.getAddress(),
                admin.getCurrentAddress()
        );

        data.setCreatedAt(
                DateFormatHelper.dateToString(admin.getCreatedAt())
        );
        data.setUpdatedAt(
                DateFormatHelper.dateToString(admin.getUpdatedAt())
        );

        return data;
    }

    public void assignOrderToShipper(AssignOrderRequest data) {
        Order order = orderRepository.findById(data.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found"));
        Shipper shipper = shipperRepository.findById(data.getShipperId()).orElseThrow(() -> new RuntimeException("Shipper not found"));
        List<ShipperOrder> shipperOrders = shipperOrderRepository.findByShipperAndOrder(shipper, order);

        if (shipperOrders.isEmpty()) {
            shipperOrderRepository.save(new ShipperOrder(order, shipper));
            order.setStatus(OrderStatusService.TRANSFER_SHIPPER).setShippingTime(
                    DateFormatHelper.stringToDate(data.getShippingTime())
            );

            OrderLog orderLog = orderLogService.createStatusLog(
                    order,
                    OrderStatusService.TRANSFER_SHIPPER,
                    String.format("Assign order %s to shipper %s", order.getOrderCode(), shipper.getShipperCode())
                );
            order.getHistories().add(orderLog);

            orderRepository.save(order);
        }
    }
}
