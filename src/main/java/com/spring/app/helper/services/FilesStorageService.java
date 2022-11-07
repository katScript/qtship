package com.spring.app.helper.services;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FilesStorageService {
    public void init();

    public void init(String path);

    public Resource save(MultipartFile file);
    public Resource save(MultipartFile file, String path);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();
}
