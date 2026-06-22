package com.example.ecommerceapplication.service.serviceimpl;

import com.example.ecommerceapplication.entity.Category;
import com.example.ecommerceapplication.entity.Product;
import com.example.ecommerceapplication.exception.ResourceNotFoundException;
import com.example.ecommerceapplication.repository.ProductRepository;
import com.example.ecommerceapplication.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        Product existingProduct = getProductById(id);

        existingProduct.setName(productDetails.getName());
        existingProduct.setDescription(productDetails.getDescription());
        existingProduct.setPrice(productDetails.getPrice());
        existingProduct.setStock(productDetails.getStock());
        existingProduct.setImageUrl(productDetails.getImageUrl());
        existingProduct.setBrand(productDetails.getBrand());

        // Update category if provided
        if (productDetails.getCategory() != null) {
            existingProduct.setCategory(productDetails.getCategory());
        }

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
}
