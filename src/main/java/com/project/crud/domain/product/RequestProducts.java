package com.project.crud.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestProducts(

        String id,
        @NotBlank
        String name,
        @NotNull
        Integer price_in_cents) {
}
