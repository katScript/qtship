package com.spring.app.admin.config.components;


import com.spring.app.admin.config.models.AdminConfig;
import com.spring.app.admin.config.models.repository.AdminConfigRepository;
import com.spring.app.patch.models.Patch;
import com.spring.app.patch.models.repository.PatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DefaultConfig implements CommandLineRunner {
    @Autowired
    PatchRepository patchRepository;

    @Autowired
    AdminConfigRepository adminConfigRepository;

    private final String PATCH = "DefaultConfigSeeder";

    @Override
    public void run(String... args) throws Exception {
        Boolean patch = patchRepository.existsByPatch(PATCH);

        if (!patch) {
            System.out.println("Cleaning config scope fee...");
            adminConfigRepository.deleteAdminConfigByScope("fee");

            System.out.println("Importing config scope fee...");
            importAdminConfigData();

            patchRepository.save(new Patch(PATCH));
            System.out.println("Import config done.");
        }
    }

    public void importAdminConfigData() {
        adminConfigRepository.saveAll(
                new ArrayList<AdminConfig>() {{
                    add(new AdminConfig(
                            "VAT",
                            "fee",
                            "int",
                            "0"
                    ));
                    add(new AdminConfig(
                            "fuel",
                            "fee",
                            "int",
                            "0"
                    ));
                    add(new AdminConfig(
                            "weight_limit",
                            "fee",
                            "int",
                            "0"
                    ));
                    add(new AdminConfig(
                            "advance_fee_weight",
                            "fee",
                            "int",
                            "0"
                    ));
                }}
        );
    }
}
