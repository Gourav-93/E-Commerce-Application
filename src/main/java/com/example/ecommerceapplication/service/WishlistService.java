package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.dto.WishlistDTO;

public interface WishlistService {
    void addToWishlist(Long userId, Long productId);
    void removeFromWishlist(Long userId, Long productId);
    WishlistDTO getWishlistByUserId(Long userId);
    void clearWishlist(Long userId);
}
