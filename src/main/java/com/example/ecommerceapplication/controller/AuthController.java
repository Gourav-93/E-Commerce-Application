package com.example.ecommerceapplication.controller;

import com.example.ecommerceapplication.dto.ApiResponse;
import com.example.ecommerceapplication.dto.AuthResponse;
import com.example.ecommerceapplication.dto.LoginRequest;
import com.example.ecommerceapplication.dto.RegisterRequest;
import com.example.ecommerceapplication.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody RegisterRequest request) {
        String message = authService.register(request);
        return new ResponseEntity<>(new ApiResponse(message, true), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}