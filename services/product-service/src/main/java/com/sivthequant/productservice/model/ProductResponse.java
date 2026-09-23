package com.sivthequant.productservice.model;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductResponse(
        Long id, String name, String description,
        BigDecimal price, Integer quantity,
        Instant createdAt, Instant updatedAt
) {
    public static ProductResponse from(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}