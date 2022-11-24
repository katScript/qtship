package com.spring.app.helper.file.services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FilesStorageService {
    String getPath();
    FilesStorageService setPath(String path);
    FilesStorageService clearPath();
    void init();
    Resource save(MultipartFile file, String path);
    Resource load(String filename);
    void deleteAll();
    Stream<Path> loadAll();

    void deleteByName(String filename);
}
