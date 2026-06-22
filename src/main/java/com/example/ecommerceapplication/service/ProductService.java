package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.entity.Product;
import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> searchProducts(String keyword);
}
