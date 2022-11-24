package com.spring.app.helper.file.services.csv;

import com.spring.app.helper.file.services.FilesStorageServiceImpl;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PriceRuleServiceImpl extends FilesStorageServiceImpl {
    @Override
    public Path getRoot() {
        if (this.path != null)
            return Paths.get(basePath + "src/main/resources/files/price/" + path);

        return Paths.get(basePath + "src/main/resources/files/price");
    }

}
