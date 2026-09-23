package com.sivthequant.productservice.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank @Size(max = 255) String name,
        @Size(max = 1000) String description,
        @NotNull @DecimalMin("0.00") BigDecimal price,
        @NotNull @Min(0) Integer quantity )
{}
