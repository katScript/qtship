package com.spring.app.products.service;

import com.spring.app.helper.services.FilesStorageServiceImpl;
import com.spring.app.products.models.Package;
import com.spring.app.products.models.Product;
import com.spring.app.products.payload.response.PackageResponse;
import com.spring.app.products.payload.response.ProductDetailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    FilesStorageServiceImpl storageService;

    public ProductDetailResponse processProductDataResponse(Product product) {
        ProductDetailResponse pD = new ProductDetailResponse(
                product.getId(),
                product.getCustomer().getCustomerId(),
                product.getCustomer().getFullName(),
                product.getSku(),
                product.getQty(),
                product.getName(),
                product.getWeight(),
                product.getBasePrice(),
                product.getPublicPrice(),
                product.getDescription(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );

        pD.setImage(
                product.getImage() != null ?
                        storageService.getImageUrl(product.getCustomer().getCustomerId() + "/" + product.getImage()) :
                        storageService.getImageUrl(FilesStorageServiceImpl.DEFAULT)
        );

        return pD;
    }

    public PackageResponse processPackageProductResponse(Package data) {
        Product product = data.getProduct();
        PackageResponse pk = new PackageResponse(
                data.getId(),
                product.getId(),
                product.getCustomer().getCustomerId(),
                product.getCustomer().getFullName(),
                product.getSku(),
                data.getQty(),
                product.getName(),
                product.getWeight(),
                product.getBasePrice(),
                product.getPublicPrice(),
                product.getDescription()
        );

        pk.setImage(
                data.getProduct().getImage() != null ?
                        storageService.getImageUrl(product.getCustomer().getCustomerId() + "/" + product.getImage()) :
                        storageService.getImageUrl(FilesStorageServiceImpl.DEFAULT)
        );

        return pk;
    }
}
