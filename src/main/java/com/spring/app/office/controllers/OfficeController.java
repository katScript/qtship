package com.spring.app.office.controllers;


import com.spring.app.office.models.Office;
import com.spring.app.office.models.repository.OfficeRepository;
import com.spring.app.office.payload.OfficeData;
import com.spring.app.office.service.OfficeService;
import com.spring.app.payload.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/office")
public class OfficeController {
    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    OfficeService officeService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllOffice() {
        List<Office> offices = officeRepository.findAll();
        List<OfficeData> officeData = new ArrayList<>();

        for (Office o : offices) {
            officeData.add(officeService.processOfficeDataResponse(o));
        }

        return ResponseEntity.ok(officeData);
    }

    @GetMapping("/all/province/{id}")
    public ResponseEntity<?> getAllOfficeByProvince(@Valid @PathVariable String id) {
        List<Office> offices = officeRepository.findByProvinceId(id);
        List<OfficeData> officeData = new ArrayList<>();

        for (Office o : offices) {
            officeData.add(officeService.processOfficeDataResponse(o));
        }

        return ResponseEntity.ok(officeData);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveOffice(@Valid @RequestBody OfficeData od) {
        try {
            officeService.saveOfficeData(od);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Office data save success!"));
    }
}
