package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.dto.AuthResponse;
import com.example.ecommerceapplication.dto.LoginRequest;
import com.example.ecommerceapplication.dto.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
