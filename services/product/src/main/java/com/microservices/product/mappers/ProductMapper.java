package com.microservices.product.mappers;

import com.microservices.product.data.models.categories.Category;
import com.microservices.product.data.models.categories.Product;
import com.microservices.product.dto.requests.ProductRequest;
import com.microservices.product.dto.responses.ProductPurchaseResponse;
import com.microservices.product.dto.responses.ProductResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(ProductRequest productRequest) {
    return Product.builder()
            .id(productRequest.id())
            .name(productRequest.name())
            .description(productRequest.description())
            .price(productRequest.price())
            .availableQuantity(productRequest.availableQuantity())
            .category(Category.builder()
                    .id(productRequest.categoryId())
                    .build())
            .build();
    }


    public ProductResponse toProductResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );

    }

    public ProductPurchaseResponse toProductPurchaseresponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(), product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
