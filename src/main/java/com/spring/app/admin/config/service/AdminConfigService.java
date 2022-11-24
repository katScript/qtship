package com.spring.app.admin.config.service;

import com.spring.app.admin.config.models.AdminConfig;
import com.spring.app.admin.config.models.repository.AdminConfigRepository;
import com.spring.app.admin.config.payload.ConfigData;
import com.spring.app.helper.date.DateFormatHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminConfigService {
    @Autowired
    AdminConfigRepository adminConfigRepository;

    public void saveAdminConfig(ConfigData cd) {
        AdminConfig adminConfig;
        if (cd.getId() != null) {
            adminConfig = adminConfigRepository.findById(cd.getId())
                    .orElseThrow(() -> new RuntimeException("Can not find config by id = " + cd.getId().toString()));
        } else {
            adminConfig = new AdminConfig();
            adminConfig.setKey(cd.getKey())
                    .setScope(cd.getScope())
                    .setType(cd.getType());
        }

        adminConfig.setValue(cd.getValue());
        adminConfigRepository.save(adminConfig);
    }

    public ConfigData processAdminConfigResponse(AdminConfig ac) {
        return new ConfigData(
                ac.getId(),
                ac.getKey(),
                ac.getScope(),
                ac.getType(),
                ac.getValue(),
                DateFormatHelper.dateToString(ac.getCreatedAt()),
                DateFormatHelper.dateToString(ac.getUpdatedAt())
        );
    }
}
