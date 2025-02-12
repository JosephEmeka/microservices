package com.microservices.product.services;

import com.microservices.product.data.models.categories.Product;
import com.microservices.product.data.repositories.ProductRepository;
import com.microservices.product.dto.requests.ProductPurchaseRequest;
import com.microservices.product.dto.requests.ProductRequest;
import com.microservices.product.dto.responses.ProductPurchaseResponse;
import com.microservices.product.dto.responses.ProductResponse;
import com.microservices.product.exceptions.ProductPurchaseException;

import com.microservices.product.mappers.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductServices {
    
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct (productRequest);
        return productRepository.save(product).getId();

    }

    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> purchaseProductRequests) {
         var productIds = purchaseProductRequests
                .stream()
                 .map(ProductPurchaseRequest::productId)
                 .toList();
         var storedProducts = productRepository.findAllByIdInOrderById(productIds);
            if(productIds.size() != storedProducts.size()){
                throw new ProductPurchaseException("One or more product does not exist");
            }
        var storedRequests = purchaseProductRequests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        IntStream.range(0, storedProducts.size()).forEach(i -> {
            var product = storedProducts.get(i);
            var productRequest = storedRequests.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }

            var newAvailableQuantity = product.getAvailableQuantity()- productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseresponse(product, productRequest.quantity()));
        });
        return purchasedProducts;
    }

    @Override
    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(()-> new EntityNotFoundException("Product with ID:" + productId + "not found"));
    }

    @Override
    public List<ProductResponse> findAll() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
