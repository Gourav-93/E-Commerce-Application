package com.example.ecommerceapplication.controller;

import com.example.ecommerceapplication.dto.ApiResponse;
import com.example.ecommerceapplication.dto.CartDTO;
import com.example.ecommerceapplication.dto.CartRequest;
import com.example.ecommerceapplication.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.example.ecommerceapplication.entity.User;
import com.example.ecommerceapplication.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/carts")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody CartRequest request) {
        
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        request.setUserId(user.getId());
        
        cartService.addToCart(request);
        return new ResponseEntity<>(new ApiResponse("Product added to cart successfully", true), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateQuantity(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody CartRequest request) {
            
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        cartService.updateQuantity(user.getId(), request.getProductId(), request.getQuantity());
        return new ResponseEntity<>(new ApiResponse("Cart updated successfully", true), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CartDTO>> getAllCartItems() {
        List<CartDTO> carts = cartService.getAllCarts();
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCart(@PathVariable Long id) {
        cartService.clearCart(id);
        return new ResponseEntity<>(new ApiResponse("Cart cleared successfully", true), HttpStatus.OK);
    }
}
