package com.spring.app.admin.config.controllers;

import com.spring.app.admin.config.models.AdminConfig;
import com.spring.app.admin.config.models.repository.AdminConfigRepository;
import com.spring.app.admin.config.payload.ConfigData;
import com.spring.app.admin.config.service.AdminConfigService;
import com.spring.app.payload.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/admin/config")
public class AdminConfigController {
    @Autowired
    AdminConfigRepository adminConfigRepository;

    @Autowired
    AdminConfigService adminConfigService;

    @GetMapping("/scope/{scope}")
    public ResponseEntity<?> getAdminConfigByScope(@Valid @PathVariable String scope) {
        List<AdminConfig> adminConfigs = adminConfigRepository.findByScope(scope);
        List<ConfigData> configData = new ArrayList<>();
        for (AdminConfig ac : adminConfigs) {
            configData.add(adminConfigService.processAdminConfigResponse(ac));
        }

        return ResponseEntity.ok(configData);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveConfig(@Valid @RequestBody ConfigData cd) {
        try {
            adminConfigService.saveAdminConfig(cd);
            return ResponseEntity.ok(new MessageResponse("save config success!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
