package com.microservices.product.dto.responses;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        Integer categoryId,

        String categoryName,

        String categoryDescription

) {
}
