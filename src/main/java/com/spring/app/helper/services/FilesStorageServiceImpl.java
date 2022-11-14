package com.spring.app.helper.services;

import java.awt.*;
import java.awt.image.BufferedImage;
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
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
    @Value("${qt.app.serviceBasePath}")
    private String basePath;

    public static final String DEFAULT = "img-default.jpg";

    private String path;

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
            return Paths.get(basePath + "/views/src/images/" + path);

        return Paths.get(basePath + "/views/src/images");
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

            String filename = name + ".png";

            Path fileResolve = this.getRoot().resolve(filename);

            BufferedImage image = ImageIO.read(file.getInputStream());
            Image scaledImage = image.getScaledInstance(400, 400, Image.SCALE_DEFAULT);
            ImageIO.write(
                    this.convertToBufferedImage(scaledImage),
                    "png",
                    new File(fileResolve.toString())
            );

            return this.load(filename);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    private BufferedImage convertToBufferedImage(Image img) {
        if (img instanceof BufferedImage)
            return (BufferedImage) img;

        BufferedImage bi = new BufferedImage(
                img.getWidth(null), img.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = bi.createGraphics();
        graphics2D.drawImage(img, 0, 0, null);
        graphics2D.dispose();

        return bi;
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

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        return Files.readAllBytes(new File(
                this.getRoot().resolve(fileName).toString()
        ).toPath());
    }
}