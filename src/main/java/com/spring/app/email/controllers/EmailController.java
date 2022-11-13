package com.spring.app.email.controllers;

import com.spring.app.email.models.Mail;
import com.spring.app.email.payload.ResetPasswordRequest;
import com.spring.app.email.service.MailServiceImp;
import com.spring.app.payload.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/email")
public class EmailController {
    @Autowired
    MailServiceImp mailServiceImp;

    @PostMapping("/auth/customer/reset")
    public ResponseEntity<?> sendCustomerResetPasswordEmail(@Valid @RequestBody ResetPasswordRequest request) {
        Mail mail = new Mail();
        mail.setMailFrom("qt.shop@gmail.com");
        mail.setMailTo(request.getEmail());
        mail.setMailSubject("Reset password");
        mail.setMailContent("<h1> Reset password link </h1> <br> <a href=\"" + request.getUrl() + "/" + request.getToken() + "\">Reset Link</a>");
        this.mailServiceImp.sendEmail(mail);

        return ResponseEntity.ok(new MessageResponse("Send email success!"));
    }
}
