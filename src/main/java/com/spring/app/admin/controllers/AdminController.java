package com.spring.app.admin.controllers;

import com.spring.app.admin.models.Admin;
import com.spring.app.admin.models.repository.AdminRepository;
import com.spring.app.admin.payload.AdminData;
import com.spring.app.admin.payload.request.AssignOrderRequest;
import com.spring.app.admin.service.AdminService;
import com.spring.app.authentication.models.User;
import com.spring.app.authentication.models.repository.UserRepository;
import com.spring.app.payload.CustomPageResponse;
import com.spring.app.payload.MessageResponse;
import com.spring.app.shipping.models.ShipperOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAdmin(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "5") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Admin> adminList = adminRepository.findAll(pageable);
        List<AdminData> listAdmin = new ArrayList<>();
        CustomPageResponse responseData = new CustomPageResponse(adminList);

        for (Admin admin : adminList) {
            listAdmin.add(adminService.processAdminResponse(admin));
        }

        responseData.setContent(listAdmin);
        return ResponseEntity.ok(responseData);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detailAdmin(@Valid @PathVariable Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User account is not found."));

        Admin admin = adminRepository.findByUser(user).orElse(null);

        if (admin == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Admin is not found."));

        return ResponseEntity.ok(adminService.processAdminResponse(admin));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> removeAdmin(@Valid @PathVariable Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        adminRepository.delete(admin);
        return ResponseEntity.ok(new MessageResponse("Delete admin success!"));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveAdmin(@Valid @RequestBody AdminData adminData) {
        User user = userRepository.findByUsername(adminData.getUserName())
                .orElse(null);

        if (user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User account is not exists."));

        Admin admin = adminService.processAdminData(adminData);
        adminRepository.save(admin);

        return ResponseEntity.ok(new MessageResponse("Save success!"));
    }

    @PostMapping("/order/shipper")
    public ResponseEntity<?> assignOrderToShipper(@Valid @RequestBody List<AssignOrderRequest> request) {
        for (AssignOrderRequest r: request) {
            adminService.assignOrderToShipper(r);
        }

        return ResponseEntity.ok(new MessageResponse("Assign successfully!"));
    }
}
