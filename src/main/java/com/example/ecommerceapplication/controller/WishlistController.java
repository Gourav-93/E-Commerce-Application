package com.example.ecommerceapplication.controller;

import com.example.ecommerceapplication.dto.ApiResponse;
import com.example.ecommerceapplication.dto.WishlistDTO;
import com.example.ecommerceapplication.entity.User;
import com.example.ecommerceapplication.repository.UserRepository;
import com.example.ecommerceapplication.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin("*")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;
    private final UserRepository userRepository; // Need to fetch user id from email

    @PostMapping("/add/{productId}")
    public ResponseEntity<ApiResponse> addToWishlist(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long productId) {
        
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        wishlistService.addToWishlist(user.getId(), productId);
        return new ResponseEntity<>(new ApiResponse("Product added to wishlist", true), HttpStatus.OK);
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<ApiResponse> removeFromWishlist(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long productId) {
        
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        wishlistService.removeFromWishlist(user.getId(), productId);
        return new ResponseEntity<>(new ApiResponse("Product removed from wishlist", true), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<WishlistDTO> getMyWishlist(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        WishlistDTO wishlist = wishlistService.getWishlistByUserId(user.getId());
        return new ResponseEntity<>(wishlist, HttpStatus.OK);
    }
}
