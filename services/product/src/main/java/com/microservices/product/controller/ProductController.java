package com.microservices.product.controller;

import com.microservices.product.dto.requests.ProductPurchaseRequest;
import com.microservices.product.dto.requests.ProductRequest;
import com.microservices.product.dto.responses.ProductPurchaseResponse;
import com.microservices.product.dto.responses.ProductResponse;
import com.microservices.product.services.ProductServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServices productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct (
            @RequestBody @Valid ProductRequest productRequest
    )
    {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }


    @PostMapping    ("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> purchaseProductRequests
    ){
        return ResponseEntity.ok(productService.purchaseProducts(purchaseProductRequests));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable("product-id") Integer productId)
    {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }


}
