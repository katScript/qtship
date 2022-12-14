package com.spring.app.authentication.controllers;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.spring.app.authentication.models.User;
import com.spring.app.authentication.payload.request.LoginRequest;
import com.spring.app.authentication.payload.request.ResetPasswordRequest;
import com.spring.app.authentication.payload.response.JwtResponse;
import com.spring.app.authentication.payload.request.ForgotPasswordRequest;
import com.spring.app.customers.payload.response.ForgotPasswordResponse;
import com.spring.app.email.service.MailServiceImp;
import com.spring.app.payload.MessageResponse;
import com.spring.app.authentication.models.repository.UserRepository;
import com.spring.app.authentication.security.jwt.JwtUtils;
import com.spring.app.authentication.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController("UserAuthController")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/reset/password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPassword) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(resetPassword.getUsername(), resetPassword.getPassword()));

        if (authentication.isAuthenticated()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userRepository.findById(userDetails.getId())
                    .orElseThrow(() -> new RuntimeException("User not found!"));

            user.setPassword(encoder.encode(resetPassword.getPassword()));
            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("Update password success"));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Can not reset password!"));
    }

    // update password function //

    @PostMapping("/forget")
    public ResponseEntity<?> forgetPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        User user = userRepository.findByUsername(forgotPasswordRequest.getUsername()).orElse(null);

        if (user == null)
            return ResponseEntity.badRequest().body(new MessageResponse("User account is not exists!"));

        String jwt = jwtUtils.generateJwtTokenWithoutAuth(user);

        return ResponseEntity.ok(new ForgotPasswordResponse(jwt,
                user.getUsername(),
                user.getEmail())
        );
    }
}