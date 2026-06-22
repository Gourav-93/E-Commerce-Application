package com.example.ecommerceapplication.service.serviceimpl;

import com.example.ecommerceapplication.dto.CartDTO;
import com.example.ecommerceapplication.dto.CartRequest;
import com.example.ecommerceapplication.dto.ProductDTO;
import com.example.ecommerceapplication.entity.Cart;
import com.example.ecommerceapplication.entity.Product;
import com.example.ecommerceapplication.entity.User;
import com.example.ecommerceapplication.exception.ResourceNotFoundException;
import com.example.ecommerceapplication.repository.CartRepository;
import com.example.ecommerceapplication.repository.ProductRepository;
import com.example.ecommerceapplication.repository.UserRepository;
import com.example.ecommerceapplication.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public void addToCart(CartRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getUserId()));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", request.getProductId()));

        Cart cart = cartRepository.findByUserId(user.getId()).orElse(null);

        if (cart == null) {
            cart = Cart.builder()
                    .user(user)
                    .products(new ArrayList<>())
                    .build();
        }

        cart.getProducts().add(product);
        cartRepository.save(cart);
    }

    @Override
    public List<CartDTO> getAllCarts() {
        return cartRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CartDTO getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "user id", userId));
        return mapToDTO(cart);
    }

    @Override
    public void deleteFromCart(Long userId, Long productId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "user id", userId));

        cart.getProducts().removeIf(product -> product.getId().equals(productId));
        cartRepository.save(cart);
    }

    @Override
    public void updateQuantity(Long userId, Long productId, Integer quantity) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "user id", userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));

        // Remove all instances of this product
        cart.getProducts().removeIf(p -> p.getId().equals(productId));

        // Add 'quantity' number of instances back
        for (int i = 0; i < quantity; i++) {
            cart.getProducts().add(product);
        }

        cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "id", cartId));
        cart.getProducts().clear();
        cartRepository.save(cart);
    }

    private CartDTO mapToDTO(Cart cart) {
        List<ProductDTO> productDTOs = cart.getProducts().stream().map(product -> 
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

        return CartDTO.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .userName(cart.getUser().getName())
                .products(productDTOs)
                .build();
    }
}
