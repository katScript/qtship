package com.spring.app.products.controllers;

import com.spring.app.customers.payload.request.DeleteRequest;
import com.spring.app.payload.CustomPageResponse;
import com.spring.app.payload.FilterRequest;
import com.spring.app.payload.MessageResponse;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.products.models.Product;
import com.spring.app.products.payload.ProductData;
import com.spring.app.products.payload.request.ProductDataRequest;
import com.spring.app.products.models.repository.ProductRepository;
import com.spring.app.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllProduct(
            @Valid FilterRequest fR
    ) {
        Pageable pageable = PageRequest.of(fR.getPage(), fR.getSize());
        Page<Product> products = productRepository.findAll(pageable);
        CustomPageResponse pageResponse = new CustomPageResponse(products);
        List<ProductData> productList = new ArrayList<>();

        for (Product p : products) {
            productList.add(this.productService.processProductDataResponse(p));
        }

        pageResponse.setContent(productList);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getProductById(@Valid @PathVariable Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found!"));
        return ResponseEntity.ok(productService.processProductDataResponse(product));
    }

    @PostMapping(value = "/save",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> saveProduct(@Valid ProductDataRequest productDataRequest) {
        try {
            ProductData productData = new ProductData(
                    productDataRequest.getId(),
                    productDataRequest.getCustomerId(),
                    productDataRequest.getCustomerCode(),
                    productDataRequest.getCustomerName(),
                    productDataRequest.getSku(),
                    productDataRequest.getQty(),
                    productDataRequest.getName(),
                    productDataRequest.getWeight(),
                    productDataRequest.getBasePrice(),
                    productDataRequest.getPublicPrice(),
                    productDataRequest.getDescription()
            );

            if (productData.getCustomerId() == null)
                productService.saveProduct(productData, productDataRequest.getFile());
            else
                productService.saveProduct(productData, productDataRequest.getFile(), productData.getCustomerId());

            return ResponseEntity.ok(new MessageResponse("Product save success"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Can not save product: " + e.getMessage()));
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@Valid @RequestBody DeleteRequest deleteRequest) {
        try {
            Customer customer = customerRepository.findById(deleteRequest.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found!"));

            Product product = productRepository.findById(deleteRequest.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (!product.getCustomer().getId().equals(customer.getId()))
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Can't not delete product! Customer not valid!!"));

            String image = product.getImage(),
                    customerCode = product.getCustomer().getCustomerId();

            productRepository.delete(product);
            productService.processDeleteProductImage(customerCode, image);

            return ResponseEntity.ok(new MessageResponse("Product deleted successfully!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Can not delete product: " + e.getMessage()));
        }
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getProductByCustomerId(
            @Valid @PathVariable Long id,
            @Valid FilterRequest fR
    ) {
        Customer customer = customerRepository.findById(id)
                .orElse(null);

        if (customer != null) {
            Pageable pageable = PageRequest.of(fR.getPage(), fR.getSize());
            Page<Product> products = productRepository.findByCustomer(customer, pageable);
            List<ProductData> productList = new ArrayList<>();
            CustomPageResponse pageResponse = new CustomPageResponse(products);

            for (Product p : products) {
                productList.add(this.productService.processProductDataResponse(p));
            }

            pageResponse.setContent(productList);
            return ResponseEntity.ok(productList);
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Error: Customer is not found."));
    }
}
