package com.spring.app.products.controllers;

import com.spring.app.authentication.payload.response.MessageResponse;
import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.products.models.Product;
import com.spring.app.products.payload.response.ProductDetailResponse;
import com.spring.app.products.models.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController("CustomerAuthController")
@RequestMapping("/v1/product")
public class ProductController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getProductByCustomerId(@Valid @PathVariable Long id) {
        Customer customer = customerRepository.findById(id)
                .orElse(null);

        if (customer != null) {
            List<Product> products = productRepository.findByCustomer(customer);
            List<ProductDetailResponse> productList = new ArrayList<>();

            for (Product p : products) {
                productList.add(new ProductDetailResponse(
                        p.getId(),
                        customer.getCustomerId(),
                        customer.getFullName(),
                        p.getSku(),
                        p.getQty(),
                        p.getName(),
                        p.getWeight(),
                        p.getBasePrice(),
                        p.getPublicPrice(),
                        p.getDescription()
                ));
            }

            return ResponseEntity.ok(productList);
        }

        return ResponseEntity.ok(new MessageResponse("Error: Customer is not found."));
    }
}
