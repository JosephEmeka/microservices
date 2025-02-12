package com.microservices.product.services;

import com.microservices.product.dto.requests.ProductPurchaseRequest;
import com.microservices.product.dto.requests.ProductRequest;
import com.microservices.product.dto.responses.ProductPurchaseResponse;
import com.microservices.product.dto.responses.ProductResponse;

import java.util.List;

public interface ProductServices {

    Integer createProduct(ProductRequest productRequest);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> purchaseProductRequests);

    ProductResponse findById(Integer productId);

    List<ProductResponse> findAll();
}
