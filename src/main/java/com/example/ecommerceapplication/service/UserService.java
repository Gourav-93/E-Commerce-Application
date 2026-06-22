package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.dto.UpdateProfileRequest;
import com.example.ecommerceapplication.dto.UserDTO;

public interface UserService {
    UserDTO getUserProfile(String email);
    UserDTO updateProfile(String email, UpdateProfileRequest request);
}
