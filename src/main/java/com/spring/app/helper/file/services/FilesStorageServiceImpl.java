package com.spring.app.helper.file.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
    @Value("${qt.app.serviceBasePath}")
    protected String basePath;

    protected String path;
    public String getPath() {
        return path;
    }

    public FilesStorageServiceImpl setPath(String path) {
        this.path = path;
        return this;
    }

    public FilesStorageServiceImpl clearPath() {
        this.path = null;
        return this;
    }

    public Path getRoot() {
        if (this.path != null)
            return Paths.get(basePath + path);

        return Paths.get(basePath);
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(this.getRoot());
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public Resource save(MultipartFile file, String name) {
        try {
            if (!Files.exists(this.getRoot()))
                this.init();

            Path fileResolve = this.getRoot().resolve(name);
            Files.copy(file.getInputStream(), fileResolve, StandardCopyOption.REPLACE_EXISTING);

            return this.load(name);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteByName(String filename) {
        try {
            Files.deleteIfExists(this.getRoot().resolve(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = this.getRoot().resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(this.getRoot().toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.getRoot(), 1).filter(path -> !path.equals(this.getRoot())).map(this.getRoot()::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}