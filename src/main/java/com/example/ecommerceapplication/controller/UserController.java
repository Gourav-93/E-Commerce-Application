package com.example.ecommerceapplication.controller;

import com.example.ecommerceapplication.dto.UpdateProfileRequest;
import com.example.ecommerceapplication.dto.UserDTO;
import com.example.ecommerceapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        UserDTO userDTO = userService.getUserProfile(userDetails.getUsername());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserDTO> updateProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UpdateProfileRequest request) {
        UserDTO updatedUser = userService.updateProfile(userDetails.getUsername(), request);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
