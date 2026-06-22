package com.example.ecommerceapplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @Positive(message = "Price must be positive")
    private Double price;

    private String stock;
    private String imageUrl;
    private String brand;
    private Long categoryId;
}
