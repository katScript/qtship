package com.spring.app.admin.user.controllers;

import com.spring.app.admin.user.models.Admin;
import com.spring.app.admin.user.models.repository.AdminRepository;
import com.spring.app.admin.user.service.AdminService;
import com.spring.app.authentication.models.Role;
import com.spring.app.authentication.models.User;
import com.spring.app.authentication.models.repository.RoleRepository;
import com.spring.app.authentication.models.repository.UserRepository;
import com.spring.app.authentication.security.jwt.JwtUtils;
import com.spring.app.admin.user.payload.request.RegisterRequest;
import com.spring.app.customers.payload.request.auth.ResetPasswordRequest;
import com.spring.app.payload.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController("AdminAuthController")
@RequestMapping("/v1/admin/auth")
public class AuthController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AdminService adminService;


    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        String username = jwtUtils.getUserNameFromJwtToken(resetPasswordRequest.getToken());

        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("User account is not exists."));

        Admin admin = adminRepository.findByUser(user)
                .orElse(null);

        if (admin == null)
            return ResponseEntity.badRequest().body(new MessageResponse("Admin is not found."));

        user.setPassword(encoder.encode(resetPasswordRequest.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Password change success!"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }

        try {
            User user = new User(registerRequest.getUserName(),
                    encoder.encode(registerRequest.getPassword()),
                    registerRequest.getUserName());

            Role userRole = roleRepository.findByCode(Admin.ROLE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

            user.addRole(userRole);

            adminService.createUser(user, registerRequest.getAdminData());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(new MessageResponse("Admin registered successfully!"));
    }
}
