package com.spring.app.products.controllers;

import com.spring.app.customers.payload.request.DeleteRequest;
import com.spring.app.file.services.FilesStorageService;
import com.spring.app.file.services.FilesStorageServiceImpl;
import com.spring.app.payload.MessageResponse;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.products.models.Product;
import com.spring.app.products.payload.request.ProductDataRequest;
import com.spring.app.products.payload.response.ProductDetailResponse;
import com.spring.app.products.models.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/product")
public class ProductController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    FilesStorageServiceImpl storageService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductDetailResponse> productList = new ArrayList<>();

        for (Product p : products) {
            ProductDetailResponse pD = new ProductDetailResponse(
                    p.getId(),
                    p.getCustomer().getCustomerId(),
                    p.getCustomer().getFullName(),
                    p.getSku(),
                    p.getQty(),
                    p.getName(),
                    p.getWeight(),
                    p.getBasePrice(),
                    p.getPublicPrice(),
                    p.getDescription(),
                    p.getCreatedAt(),
                    p.getUpdatedAt()
            );

            if (p.getImage() != null) {
                pD.setImage(
                    ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .build().toUriString()
                        + "/image/product/" + p.getCustomer().getCustomerId() + "/" + p.getImage()
                );
            }

            productList.add(pD);
        }

        return ResponseEntity.ok(productList);
    }

    @PostMapping(value = "/save",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> saveProduct(@Valid ProductDataRequest productDataRequest) {
        Customer customer = customerRepository.findById(productDataRequest.getCustomerId())
                .orElse(null);

        if (customer != null) {
            try {
                Product product;
                if (productDataRequest.getId() == null) {
                    Resource resource = storageService.save(productDataRequest.getFile(), customer.getCustomerId());

                    product = new Product(
                            productDataRequest.getSku(),
                            productDataRequest.getName(),
                            productDataRequest.getQty(),
                            productDataRequest.getWeight(),
                            productDataRequest.getBasePrice(),
                            productDataRequest.getPublicPrice(),
                            productDataRequest.getDescription(),
                            resource.getFilename(),
                            customer
                    );
                } else {
                    product = productRepository.findById(productDataRequest.getId()).orElse(null);

                    if (product == null)
                        return ResponseEntity.badRequest().body(new MessageResponse("Error: Product is not found."));

                    if (!product.getCustomer().getId().equals(customer.getId()))
                        return ResponseEntity.badRequest().body(new MessageResponse("Error: Can't not save product! Customer not valid!!"));

                    product.setSku(productDataRequest.getSku())
                            .setName(productDataRequest.getName())
                            .setQty(productDataRequest.getQty())
                            .setWeight(productDataRequest.getWeight())
                            .setBasePrice(productDataRequest.getBasePrice())
                            .setPublicPrice(productDataRequest.getPublicPrice())
                            .setDescription(productDataRequest.getDescription());

                    Resource resource = storageService.save(productDataRequest.getFile(), customer.getCustomerId());
                    product.setImage(resource.getFilename());
                }

                productRepository.save(product);
                return ResponseEntity.ok(new MessageResponse("Product save success"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        return ResponseEntity.badRequest().body(new MessageResponse("Error: Customer is not found."));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteProduct(@Valid @RequestBody DeleteRequest deleteRequest) {
        Customer customer = customerRepository.findById(deleteRequest.getCustomerId())
                .orElse(null);

        if (customer != null) {
            Product product = productRepository.findById(deleteRequest.getId()).orElse(null);

            if (product == null)
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Product is not found."));

            if (!product.getCustomer().getId().equals(customer.getId()))
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Can't not save product! Customer not valid!!"));

            productRepository.delete(product);
            return ResponseEntity.ok(new MessageResponse("Product deleted successfully!"));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Error: Customer not found!"));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getProductByCustomerId(@Valid @PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElse(null);

        if (customer != null) {
            List<ProductDetailResponse> productList = new ArrayList<>();

            for (Product p : customer.getProducts()) {
                ProductDetailResponse pD = new ProductDetailResponse(
                        p.getId(),
                        customer.getCustomerId(),
                        customer.getFullName(),
                        p.getSku(),
                        p.getQty(),
                        p.getName(),
                        p.getWeight(),
                        p.getBasePrice(),
                        p.getPublicPrice(),
                        p.getDescription(),
                        p.getCreatedAt(),
                        p.getUpdatedAt()
                );

                if (p.getImage() != null) {
                    pD.setImage(
                        ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .build().toUriString()
                            + "/image/product/" + p.getCustomer().getCustomerId() + "/" + p.getImage()
                    );
                }

                productList.add(pD);
            }

            return ResponseEntity.ok(productList);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Error: Customer is not found."));
    }
}
