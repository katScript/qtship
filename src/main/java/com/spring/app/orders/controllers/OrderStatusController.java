package com.spring.app.orders.controllers;

import com.spring.app.helper.date.DateFormatHelper;
import com.spring.app.orders.models.OrderStatus;
import com.spring.app.orders.models.repository.OrderStatusRepository;
import com.spring.app.orders.payload.OrderStatusData;
import com.spring.app.orders.services.OrderStatusService;
import com.spring.app.payload.CustomPageResponse;
import com.spring.app.payload.FilterRequest;
import com.spring.app.payload.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/order/status")
public class OrderStatusController {
    @Autowired
    OrderStatusRepository orderStatusRepository;
    @Autowired
    OrderStatusService orderStatusService;

    @PostMapping("/save")
    public ResponseEntity<?> saveOrderStatus(@Valid @RequestBody OrderStatusData orderStatus) {
        OrderStatus status = orderStatusRepository.findByCode(orderStatus.getCode())
                .orElse(new OrderStatus(
                        orderStatus.getCode(),
                        orderStatus.getLabel(),
                        orderStatus.getEnable()
                ));

        Set<OrderStatus> child = new HashSet<>();

        for (String c : orderStatus.getChild()) {
            child.add(orderStatusRepository.findByCode(c)
                    .orElseThrow(() -> new RuntimeException("Can not find order status!")));
        }

        status.setChild(child);
        this.orderStatusRepository.save(status);

        return ResponseEntity.ok(new MessageResponse("Status save successfully!"));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrderStatus(
            @Valid FilterRequest fR
    ) {
        Pageable pageable = PageRequest.of(fR.getPage(), fR.getSize());
        Page<OrderStatus> orderStatuses = orderStatusRepository.findAll(pageable);
        CustomPageResponse pageResponse = new CustomPageResponse(orderStatuses);
        List<OrderStatusData> orderStatusDataList = new ArrayList<>();

        for (OrderStatus os: orderStatuses) {
            orderStatusDataList.add(orderStatusService.processOrderStatusData(os));
        }

        pageResponse.setContent(orderStatusDataList);
        return ResponseEntity.ok(pageResponse);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@Valid @PathVariable Long id) {
        OrderStatus orderStatus = orderStatusRepository.findById(id).orElse(null);

        if (orderStatus != null) {
            List<String> child = new ArrayList<>();

            for (OrderStatus os: orderStatus.getChild()) {
                child.add(os.getCode());
            }

            OrderStatusData orderStatusData = new OrderStatusData(
                    orderStatus.getId(),
                    orderStatus.getCode(),
                    orderStatus.getLabel(),
                    orderStatus.getEnable(),
                    child
            );

            orderStatusData.setCreatedAt(
                    DateFormatHelper.dateToString(orderStatus.getCreatedAt())
            );
            orderStatusData.setUpdatedAt(
                    DateFormatHelper.dateToString(orderStatus.getUpdatedAt())
            );

            return ResponseEntity.ok(orderStatusData);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Order status not found!"));
    }

    @GetMapping("/detail/code/{code}")
    public ResponseEntity<?> getByStatusCode(@Valid @PathVariable String code) {
        OrderStatus orderStatus = orderStatusRepository.findByCode(code).orElse(null);

        if (orderStatus != null) {
            List<String> child = new ArrayList<>();

            for (OrderStatus os: orderStatus.getChild()) {
                child.add(os.getCode());
            }

            OrderStatusData orderStatusData = new OrderStatusData(
                    orderStatus.getId(),
                    orderStatus.getCode(),
                    orderStatus.getLabel(),
                    orderStatus.getEnable(),
                    child
            );

            orderStatusData.setCreatedAt(
                    DateFormatHelper.dateToString(orderStatus.getCreatedAt())
            );
            orderStatusData.setUpdatedAt(
                    DateFormatHelper.dateToString(orderStatus.getUpdatedAt())
            );

            return ResponseEntity.ok(orderStatusData);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Order status not found!"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable Long id) {
        OrderStatus orderStatus = orderStatusRepository.findById(id).orElse(null);

        if (orderStatus != null) {
            orderStatusRepository.delete(orderStatus);
            return ResponseEntity.ok(new MessageResponse("Delete order success!"));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Order status not found!"));
    }
}
