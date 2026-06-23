package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.dto.OrderDTO;
import com.example.ecommerceapplication.dto.OrderRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    OrderDTO placeOrder(OrderRequest request);
    Page<OrderDTO> getAllOrders(Pageable pageable);
    Page<OrderDTO> getOrdersByUserId(Long userId, Pageable pageable);
    OrderDTO cancelOrder(Long orderId);
}
