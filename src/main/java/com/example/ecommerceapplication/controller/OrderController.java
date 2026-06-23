package com.example.ecommerceapplication.controller;

import com.example.ecommerceapplication.dto.ApiResponse;
import com.example.ecommerceapplication.dto.OrderDTO;
import com.example.ecommerceapplication.dto.OrderRequest;
import com.example.ecommerceapplication.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.example.ecommerceapplication.entity.User;
import com.example.ecommerceapplication.repository.UserRepository;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;

    @PostMapping("/place")
    public ResponseEntity<ApiResponse> placeOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody OrderRequest request) {
            
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        request.setUserId(user.getId());
        
        orderService.placeOrder(request);
        return new ResponseEntity<>(new ApiResponse("Order placed successfully", true), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<Page<OrderDTO>> getAllOrders(@PageableDefault(size = 10) Pageable pageable) {
        return new ResponseEntity<>(orderService.getAllOrders(pageable), HttpStatus.OK);
    }

    @GetMapping("/my-orders")
    public ResponseEntity<Page<OrderDTO>> getMyOrders(
            @AuthenticationPrincipal UserDetails userDetails,
            @PageableDefault(size = 10) Pageable pageable) {
        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        return new ResponseEntity<>(orderService.getOrdersByUserId(user.getId(), pageable), HttpStatus.OK);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<ApiResponse> cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return new ResponseEntity<>(new ApiResponse("Order cancelled", true), HttpStatus.OK);
    }
}