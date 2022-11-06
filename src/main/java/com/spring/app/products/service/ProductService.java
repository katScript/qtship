package com.spring.app.products.service;

import com.spring.app.fileManager.services.FilesStorageServiceImpl;
import com.spring.app.products.models.Product;
import com.spring.app.products.payload.response.ProductDetailResponse;

public class ProductService {
    FilesStorageServiceImpl storageService = new FilesStorageServiceImpl();

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
}
