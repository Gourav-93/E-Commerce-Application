package com.example.ecommerceapplication.controller;

import com.example.ecommerceapplication.dto.ApiResponse;
import com.example.ecommerceapplication.dto.ProductDTO;
import com.example.ecommerceapplication.dto.ProductRequest;
import com.example.ecommerceapplication.entity.Category;
import com.example.ecommerceapplication.entity.Product;
import com.example.ecommerceapplication.service.CategoryService;
import com.example.ecommerceapplication.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@Valid @RequestBody ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setImageUrl(request.getImageUrl());
        product.setBrand(request.getBrand());

        if (request.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(request.getCategoryId());
            product.setCategory(category);
        }

        productService.addProduct(product);

        return new ResponseEntity<>(new ApiResponse("Product added successfully", true), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOs = products.stream().map(this::mapToDTO).collect(Collectors.toList());
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(mapToDTO(product), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam String keyword) {
        List<Product> products = productService.searchProducts(keyword);
        List<ProductDTO> productDTOs = products.stream().map(this::mapToDTO).collect(Collectors.toList());
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/product/{id}")
    public ResponseEntity<ApiResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        Product productDetails = new Product();
        productDetails.setName(request.getName());
        productDetails.setDescription(request.getDescription());
        productDetails.setPrice(request.getPrice());
        productDetails.setStock(request.getStock());
        productDetails.setImageUrl(request.getImageUrl());
        productDetails.setBrand(request.getBrand());

        if (request.getCategoryId() != null) {
            Category category = categoryService.getCategoryById(request.getCategoryId());
            productDetails.setCategory(category);
        }

        productService.updateProduct(id, productDetails);

        return new ResponseEntity<>(new ApiResponse("Product updated successfully", true), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/product/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(new ApiResponse("Product deleted successfully", true), HttpStatus.OK);
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