package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.dto.ProductDTO;
import com.example.ecommerceapplication.dto.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductDTO addProduct(ProductRequest productRequest);
    Page<ProductDTO> getAllProducts(Pageable pageable);
    ProductDTO getProductById(Long id);
    ProductDTO updateProduct(Long id, ProductRequest productRequest);
    void deleteProduct(Long id);
    Page<ProductDTO> searchProducts(String keyword, Pageable pageable);
}
