package com.spring.app.helper.controllers;

import com.spring.app.customers.service.CustomerService;
import com.spring.app.helper.services.FilesStorageServiceImpl;
import com.spring.app.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/image")
public class FileController {
    @Autowired
    private FilesStorageServiceImpl service;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/product/{path}/{fileName}")
    public ResponseEntity<?> productImageByCustomer(@Valid @PathVariable String path, @PathVariable String fileName) throws IOException {
        service.setPath(productService.getImageScope(path));
        byte[] imageData = service.downloadImageFromFileSystem(fileName);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @GetMapping("/product/{fileName}")
    public ResponseEntity<?> productImage(@Valid @PathVariable String fileName) throws IOException {
        service.setPath(ProductService.SCOPE);
        byte[] imageData = service.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @GetMapping("/customer/{path}/{fileName}")
    public ResponseEntity<?> getCustomerImageByCustomerCode(@Valid @PathVariable String path, @PathVariable String fileName) throws IOException {
        service.setPath(customerService.getImageScope(path));
        byte[] imageData = service.downloadImageFromFileSystem(fileName);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @GetMapping("/customer/{fileName}")
    public ResponseEntity<?> getCustomerImage(@Valid @PathVariable String fileName) throws IOException {
        service.setPath(CustomerService.SCOPE);
        byte[] imageData = service.downloadImageFromFileSystem(fileName);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
