package com.example.ecommerceapplication.controller;

import com.example.ecommerceapplication.dto.ApiResponse;
import com.example.ecommerceapplication.dto.ProductDTO;
import com.example.ecommerceapplication.dto.ProductRequest;
import com.example.ecommerceapplication.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@Valid @RequestBody ProductRequest request) {
        productService.addProduct(request);
        return new ResponseEntity<>(new ApiResponse("Product added successfully", true), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ProductDTO>> getAllProducts(@PageableDefault(size = 1000) Pageable pageable) {
        Page<ProductDTO> products = productService.getAllProducts(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductDTO>> searchProducts(@RequestParam String keyword, @PageableDefault(size = 1000) Pageable pageable) {
        Page<ProductDTO> products = productService.searchProducts(keyword, pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/product/{id}")
    public ResponseEntity<ApiResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        productService.updateProduct(id, request);
        return new ResponseEntity<>(new ApiResponse("Product updated successfully", true), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/product/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(new ApiResponse("Product deleted successfully", true), HttpStatus.OK);
    }
}