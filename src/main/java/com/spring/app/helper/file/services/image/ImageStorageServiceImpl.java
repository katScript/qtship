package com.spring.app.helper.file.services.image;

import com.spring.app.helper.file.services.FilesStorageServiceImpl;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageStorageServiceImpl extends FilesStorageServiceImpl {
    public static final String DEFAULT = "img-default.jpg";

    @Override
    public Path getRoot() {
        if (this.path != null)
            return Paths.get(basePath + "views/src/images/" + path);

        return Paths.get(basePath + "views/src/images");
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

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        return Files.readAllBytes(new File(
                this.getRoot().resolve(fileName).toString()
        ).toPath());
    }
}
