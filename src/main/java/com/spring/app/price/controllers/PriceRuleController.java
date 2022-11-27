package com.spring.app.price.controllers;

import com.spring.app.payload.MessageResponse;
import com.spring.app.price.payload.request.PriceRuleImportRequest;
import com.spring.app.price.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/office/price")
public class PriceRuleController {
    @Autowired
    RuleService ruleService;

    @PostMapping("/import")
    public ResponseEntity<?> importPriceConfigData(@Valid PriceRuleImportRequest request) {
        try {
            ruleService.saveRule(request);
            return ResponseEntity.ok(new MessageResponse("Import rule data success!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
