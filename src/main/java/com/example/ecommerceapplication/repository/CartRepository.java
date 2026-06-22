package com.example.ecommerceapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.example.ecommerceapplication.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
}
