package com.spring.app.products.service;

import com.spring.app.customers.models.Customer;
import com.spring.app.customers.models.repository.CustomerRepository;
import com.spring.app.helper.services.DateFormatHelper;
import com.spring.app.helper.services.FilesStorageServiceImpl;
import com.spring.app.orders.models.Order;
import com.spring.app.products.models.Package;
import com.spring.app.products.models.Product;
import com.spring.app.products.models.repository.PackageRepository;
import com.spring.app.products.models.repository.ProductRepository;
import com.spring.app.products.payload.PackageData;
import com.spring.app.products.payload.ProductData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;

@Service
public class ProductService {
    public static final String SCOPE = "product";

    @Autowired
    FilesStorageServiceImpl storageService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    PackageRepository packageRepository;

    public void saveProduct(ProductData productData, MultipartFile file) {
        Product product = processProductData(productData);

        product.setImage(processUploadProductImage(
                file,
                Customer.GUEST_CODE,
                product)
        );

        productRepository.save(product);
    }

    public void saveProduct(ProductData productData, MultipartFile file, Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Product product = processProductData(productData);
        product.setCustomer(customer)
                .setImage(processUploadProductImage(
                        file,
                        customer.getCustomerId(),
                        product)
                );

        productRepository.save(product);

    }

    public Product processProductData(ProductData productData) {
        Product product;

        if (productData.getId() != null) {
            product = productRepository.findById(productData.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found!"));
        } else {
            product = new Product();
        }

        product.setSku(productData.getSku())
                .setName(productData.getName())
                .setQty(productData.getQty())
                .setWeight(productData.getWeight())
                .setBasePrice(productData.getBasePrice())
                .setPublicPrice(productData.getPublicPrice())
                .setDescription(productData.getDescription());


        return product;
    }

    public String processUploadProductImage(MultipartFile file, String customerCode, Product product) {
        Resource resource = null;

        if (file != null && !file.isEmpty()) {
            storageService.setPath(getImageScope(customerCode));
            resource = storageService.save(file, product.getSku());
        }

        return resource != null ? resource.getFilename() : product.getImage();
    }

    public ProductData processProductDataResponse(Product product) {
        ProductData pD;

        if (product != null) {
            pD = new ProductData(
                    product.getId(),
                    product.getCustomer().getId(),
                    product.getCustomer().getCustomerId(),
                    product.getCustomer().getFullName(),
                    product.getSku(),
                    product.getQty(),
                    product.getName(),
                    product.getWeight(),
                    product.getBasePrice(),
                    product.getPublicPrice(),
                    product.getDescription()
            );

            pD.setImage(processProductImage(product));
            pD.setCreatedAt(
                    DateFormatHelper.dateToString(product.getCreatedAt())
            );
            pD.setUpdatedAt(
                    DateFormatHelper.dateToString(product.getUpdatedAt())
            );
        } else {
            pD = new ProductData();
        }

        return pD;
    }

    public Package processPackage(PackageData data) {
        Package packageItem;

        if (data.getId() == null) {
            packageItem = new Package();
        } else {
            packageItem = this.packageRepository.findById(data.getId())
                    .orElseThrow(() -> new RuntimeException("Package not found!"));
        }

        if (data.getProduct() == null || data.getProduct().getId() == null) {
            packageItem.setProduct(null)
                    .setImageUrl(getImageUrl(FilesStorageServiceImpl.DEFAULT));
        } else {
            Product product = productRepository.findById(data.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found!"));

            packageItem.setProduct(product)
                    .setImageUrl(this.processProductImage(product));
        }

        packageItem.setPrice(data.getPrice())
                .setName(data.getName())
                .setQty(data.getQty())
                .setWeight(data.getWeight())
                .setLongPackage(data.getLongPackage())
                .setWidthPackage(data.getWidthPackage())
                .setHeightPackage(data.getHeightPackage())
                .setSpecialType(data.getSpecialType());

        return packageItem;
    }

    public PackageData processPackageProductResponse(Package data) {
        Product product = data.getProduct();

        PackageData pD = new PackageData(
                data.getId(),
                this.processProductDataResponse(product),
                data.getName(),
                data.getQty(),
                data.getPrice(),
                data.getWeight(),
                data.getImageUrl()
        );

        pD.setCreatedAt(
                DateFormatHelper.dateToString(data.getCreatedAt())
        );
        pD.setUpdatedAt(
                DateFormatHelper.dateToString(data.getUpdatedAt())
        );

        return pD;
    }

    public String processProductImage(Product product) {
        return product.getImage() != null ?
                getImageUrl(product.getCustomer().getCustomerId() + File.separator + product.getImage()) :
                getImageUrl(FilesStorageServiceImpl.DEFAULT);
    }

    public void processDeleteProductImage(String customerCode, String image) {
        storageService.setPath(getImageScope(customerCode)).deleteByName(image);
    }

    public String getImageScope(String path) {
        return SCOPE + File.separator + path;
    }

    public String getImageUrl(String path) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .build().toUriString()
                + "/image/product/" + path;
    }
}
