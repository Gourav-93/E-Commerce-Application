package com.example.ecommerceapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerceapplication.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}