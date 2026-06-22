package com.example.ecommerceapplication.service.serviceimpl;

import com.example.ecommerceapplication.dto.AuthResponse;
import com.example.ecommerceapplication.dto.LoginRequest;
import com.example.ecommerceapplication.dto.RegisterRequest;
import com.example.ecommerceapplication.dto.UserDTO;
import com.example.ecommerceapplication.entity.User;
import com.example.ecommerceapplication.enums.Role;
import com.example.ecommerceapplication.exception.ApiException;
import com.example.ecommerceapplication.repository.UserRepository;
import com.example.ecommerceapplication.security.JwtUtil;
import com.example.ecommerceapplication.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email is already registered");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER) // Default role
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found"));

        var jwtToken = jwtUtil.generateToken(user);
        
        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();

        return new AuthResponse(jwtToken, userDTO);
    }
}
