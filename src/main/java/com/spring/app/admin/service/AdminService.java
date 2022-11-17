package com.spring.app.admin.service;


import com.spring.app.admin.models.Admin;
import com.spring.app.admin.models.repository.AdminRepository;
import com.spring.app.admin.payload.AdminData;
import com.spring.app.admin.payload.request.AssignOrderRequest;
import com.spring.app.authentication.models.User;
import com.spring.app.helper.services.DateFormatHelper;
import com.spring.app.orders.models.Order;
import com.spring.app.orders.models.repository.OrderRepository;
import com.spring.app.shipping.models.Shipper;
import com.spring.app.shipping.models.ShipperOrder;
import com.spring.app.shipping.models.repository.ShipperOrderRepository;
import com.spring.app.shipping.models.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Order order = orderRepository.findById(data.getOrderId()).orElse(null);
        Shipper shipper = shipperRepository.findById(data.getShipperId()).orElse(null);

        if (order != null && shipper != null)
            shipperOrderRepository.save(new ShipperOrder(order, shipper));
    }
}
