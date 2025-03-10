package com.microservices.order.dto.requests;

import com.microservices.order.constants.PaymentMethod;
import com.microservices.order.data.models.products.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "payment method should not be null")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer should not be present")
        @NotEmpty(message = "Customer should not be present")
        @NotBlank(message = "Customer should not be present")
        String customerId,
        @NotEmpty(message = "At least one product should be purchased")
        List<PurchaseRequest> products
) {
}
