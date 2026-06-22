package com.example.ecommerceapplication.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class CartDTO {
    private Long id;
    private Long userId;
    private String userName;
    private List<ProductDTO> products;
}
