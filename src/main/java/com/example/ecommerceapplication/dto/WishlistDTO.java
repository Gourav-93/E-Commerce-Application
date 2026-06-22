package com.example.ecommerceapplication.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class WishlistDTO {
    private Long id;
    private Long userId;
    private List<ProductDTO> products;
}
