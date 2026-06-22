package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.dto.OrderDTO;
import com.example.ecommerceapplication.dto.OrderRequest;

import java.util.List;

public interface OrderService {
    void placeOrder(OrderRequest request);
    List<OrderDTO> getAllOrders();
    List<OrderDTO> getOrdersByUserId(Long userId);
    void cancelOrder(Long orderId);
}
