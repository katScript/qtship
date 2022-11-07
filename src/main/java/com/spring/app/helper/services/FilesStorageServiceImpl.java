package com.spring.app.helper.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
    @Value("${qt.app.serviceBasePath}")
    private String basePath;

    public static final String DEFAULT = "img-default.jpg";

    public Path getRoot() {
        return Paths.get(basePath + "/views/src/images/product");
    }

    public Path getRoot(String path) {
        return Paths.get(basePath + "/views/src/images/product/" + path);
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
    public void init(String path) {
        try {
            Files.createDirectory(this.getRoot(path));
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public Resource save(MultipartFile file, String path) {
        try {
            if (!Files.exists(this.getRoot(path)))
                this.init(path);

            Files.deleteIfExists(this.getRoot(path).resolve(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), this.getRoot(path).resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            return this.load(path + "/" + file.getOriginalFilename());
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource save(MultipartFile file) {
        try {
            if (!Files.exists(this.getRoot()))
                this.init();

            Files.deleteIfExists(this.getRoot().resolve(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), this.getRoot().resolve(file.getOriginalFilename()), REPLACE_EXISTING);

            return this.load(file.getOriginalFilename());
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
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

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        return Files.readAllBytes(new File(basePath + "/views/src/images/product/" + fileName).toPath());
    }

    public String getImageUrl(String path) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build().toUriString()
                + "/image/product/" + path;
    }
}