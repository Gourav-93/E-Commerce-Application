package com.example.ecommerceapplication.service.serviceimpl;

import com.example.ecommerceapplication.dto.ProductDTO;
import com.example.ecommerceapplication.dto.ProductRequest;
import com.example.ecommerceapplication.entity.Category;
import com.example.ecommerceapplication.entity.Product;
import com.example.ecommerceapplication.exception.ResourceNotFoundException;
import com.example.ecommerceapplication.repository.CategoryRepository;
import com.example.ecommerceapplication.repository.ProductRepository;
import com.example.ecommerceapplication.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ProductDTO addProduct(ProductRequest productRequest) {
        log.info("Adding new product: {}", productRequest.getName());
        Category category = null;
        if (productRequest.getCategoryId() != null) {
            category = categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productRequest.getCategoryId()));
        }

        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .imageUrl(productRequest.getImageUrl())
                .brand(productRequest.getBrand())
                .category(category)
                .build();

        Product savedProduct = productRepository.save(product);
        return mapToDTO(savedProduct);
    }

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        log.info("Fetching all products with pagination");
        return productRepository.findAll(pageable).map(this::mapToDTO);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return mapToDTO(product);
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(Long id, ProductRequest productRequest) {
        log.info("Updating product id: {}", id);
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        existingProduct.setName(productRequest.getName());
        existingProduct.setDescription(productRequest.getDescription());
        existingProduct.setPrice(productRequest.getPrice());
        existingProduct.setStock(productRequest.getStock());
        existingProduct.setImageUrl(productRequest.getImageUrl());
        existingProduct.setBrand(productRequest.getBrand());

        if (productRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById(productRequest.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", productRequest.getCategoryId()));
            existingProduct.setCategory(category);
        } else {
            existingProduct.setCategory(null);
        }

        Product updatedProduct = productRepository.save(existingProduct);
        return mapToDTO(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        log.info("Deleting product id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        productRepository.delete(product);
    }

    @Override
    public Page<ProductDTO> searchProducts(String keyword, Pageable pageable) {
        log.info("Searching products with keyword: {}", keyword);
        return productRepository.findByNameContainingIgnoreCase(keyword, pageable).map(this::mapToDTO);
    }

    private ProductDTO mapToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .imageUrl(product.getImageUrl())
                .brand(product.getBrand())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .build();
    }
}
