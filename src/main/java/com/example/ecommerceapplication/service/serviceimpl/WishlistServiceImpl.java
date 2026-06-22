package com.example.ecommerceapplication.service.serviceimpl;

import com.example.ecommerceapplication.dto.ProductDTO;
import com.example.ecommerceapplication.dto.WishlistDTO;
import com.example.ecommerceapplication.entity.Product;
import com.example.ecommerceapplication.entity.User;
import com.example.ecommerceapplication.entity.Wishlist;
import com.example.ecommerceapplication.exception.ResourceNotFoundException;
import com.example.ecommerceapplication.repository.ProductRepository;
import com.example.ecommerceapplication.repository.UserRepository;
import com.example.ecommerceapplication.repository.WishlistRepository;
import com.example.ecommerceapplication.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public void addToWishlist(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        Wishlist wishlist = wishlistRepository.findByUserId(userId).orElse(null);

        if (wishlist == null) {
            wishlist = Wishlist.builder()
                    .user(user)
                    .products(new ArrayList<>())
                    .build();
        }

        // Avoid duplicates
        boolean exists = wishlist.getProducts().stream().anyMatch(p -> p.getId().equals(productId));
        if (!exists) {
            wishlist.getProducts().add(product);
            wishlistRepository.save(wishlist);
        }
    }

    @Override
    public void removeFromWishlist(Long userId, Long productId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Wishlist", "user id", userId));

        wishlist.getProducts().removeIf(product -> product.getId().equals(productId));
        wishlistRepository.save(wishlist);
    }

    @Override
    public WishlistDTO getWishlistByUserId(Long userId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
                    return Wishlist.builder().user(user).products(new ArrayList<>()).build();
                });
        return mapToDTO(wishlist);
    }

    @Override
    public void clearWishlist(Long userId) {
        Wishlist wishlist = wishlistRepository.findByUserId(userId).orElse(null);
        if (wishlist != null) {
            wishlist.getProducts().clear();
            wishlistRepository.save(wishlist);
        }
    }

    private WishlistDTO mapToDTO(Wishlist wishlist) {
        List<ProductDTO> productDTOs = wishlist.getProducts().stream().map(product ->
                ProductDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .imageUrl(product.getImageUrl())
                        .brand(product.getBrand())
                        .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                        .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                        .build()
        ).collect(Collectors.toList());

        return WishlistDTO.builder()
                .id(wishlist.getId())
                .userId(wishlist.getUser().getId())
                .products(productDTOs)
                .build();
    }
}
