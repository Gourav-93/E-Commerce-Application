package com.example.ecommerceapplication.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String stock;
    private String imageUrl;
    private String brand;
    private String categoryName;
    private Long categoryId;
}
