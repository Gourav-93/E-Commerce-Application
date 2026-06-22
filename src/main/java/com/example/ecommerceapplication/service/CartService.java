package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.dto.CartDTO;
import com.example.ecommerceapplication.dto.CartRequest;
import java.util.List;

public interface CartService {
    void addToCart(CartRequest request);
    List<CartDTO> getAllCarts();
    CartDTO getCartByUserId(Long userId);
    void deleteFromCart(Long userId, Long productId);
    void updateQuantity(Long userId, Long productId, Integer quantity);
    void clearCart(Long cartId);
}
