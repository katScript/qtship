package com.spring.app.fileManager.controllers;

import com.spring.app.fileManager.services.FilesStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/image")
public class FileController {
    @Autowired
    private FilesStorageServiceImpl service;

    @GetMapping("/product/{path}/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@Valid @PathVariable String path, @PathVariable String fileName) throws IOException {
        byte[] imageData = service.downloadImageFromFileSystem(path + "/" + fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }
}
