package com.spring.app.liquidity.controllers;

import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.helper.date.DateFormatHelper;
import com.spring.app.liquidity.models.Liquidity;
import com.spring.app.liquidity.models.repository.LiquidityRepository;
import com.spring.app.liquidity.payload.LiquidityData;
import com.spring.app.payload.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/liquidity")
public class LiquidityController {
    @Autowired
    LiquidityRepository liquidityRepository;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/all/customer/{id}")
    public ResponseEntity<?> getAllByCustomerId(@Valid @PathVariable Long id) {
        try {
            Customer customer = customerRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Customer not found!"));
            List<Liquidity> lqList = liquidityRepository.findByCustomer(customer);
            List<LiquidityData> responseList = new ArrayList<>();

            for (Liquidity lq : lqList) {
                responseList.add(
                        new LiquidityData(
                                lq.getId(),
                                lq.getCustomer().getId(),
                                lq.getMoneyReceivable(),
                                lq.getPpPm(),
                                lq.getCcCash(),
                                lq.getPpCash(),
                                lq.getForControl(),
                                lq.getReceivable(),
                                lq.getCouponCash(),
                                DateFormatHelper.dateToString(lq.getCreatedAt()),
                                DateFormatHelper.dateToString(lq.getUpdatedAt())
                        )
                );
            }

            return ResponseEntity.ok(responseList);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
