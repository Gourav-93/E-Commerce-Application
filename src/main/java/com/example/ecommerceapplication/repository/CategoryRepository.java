package com.example.ecommerceapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerceapplication.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
