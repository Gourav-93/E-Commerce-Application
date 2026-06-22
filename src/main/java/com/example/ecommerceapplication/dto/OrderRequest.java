package com.example.ecommerceapplication.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotEmpty(message = "Product IDs cannot be empty")
    private List<Long> productIds;

    @NotNull(message = "Total amount cannot be null")
    private Double totalAmount;
}
