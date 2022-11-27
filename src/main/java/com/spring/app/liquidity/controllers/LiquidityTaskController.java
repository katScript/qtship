package com.spring.app.liquidity.controllers;

import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.helper.date.DateFormatHelper;
import com.spring.app.liquidity.models.Liquidity;
import com.spring.app.liquidity.models.repository.LiquidityRepository;
import com.spring.app.liquidity.payload.LiquidityData;
import com.spring.app.liquidity.service.LiquidityService;
import com.spring.app.payload.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/liquidity/task")
public class LiquidityTaskController {

    @Autowired
    LiquidityService liquidityService;

    @GetMapping("/newSession")
    public ResponseEntity<?> createNewLiquiditySession() {
        try {
            liquidityService.createNewLiquiditySession();
            return ResponseEntity.ok(new MessageResponse("Done!"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
