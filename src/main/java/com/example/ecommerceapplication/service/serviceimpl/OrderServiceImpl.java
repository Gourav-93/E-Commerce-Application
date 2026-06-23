package com.example.ecommerceapplication.service.serviceimpl;

import com.example.ecommerceapplication.dto.OrderDTO;
import com.example.ecommerceapplication.dto.OrderRequest;
import com.example.ecommerceapplication.dto.ProductDTO;
import com.example.ecommerceapplication.entity.Order;
import com.example.ecommerceapplication.entity.Product;
import com.example.ecommerceapplication.entity.User;
import com.example.ecommerceapplication.enums.OrderStatus;
import com.example.ecommerceapplication.exception.ResourceNotFoundException;
import com.example.ecommerceapplication.repository.OrderRepository;
import com.example.ecommerceapplication.repository.ProductRepository;
import com.example.ecommerceapplication.repository.UserRepository;
import com.example.ecommerceapplication.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public OrderDTO placeOrder(OrderRequest request) {
        log.info("Placing order for user id: {}", request.getUserId());
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getUserId()));

        List<Product> products = request.getProductIds().stream()
                .map(id -> productRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id)))
                .collect(Collectors.toList());

        Order order = Order.builder()
                .user(user)
                .products(products)
                .totalAmount(request.getTotalAmount())
                .status(OrderStatus.PENDING)
                .build();

        Order savedOrder = orderRepository.save(order);
        return mapToDTO(savedOrder);
    }

    @Override
    public Page<OrderDTO> getAllOrders(Pageable pageable) {
        log.info("Fetching all orders with pagination");
        return orderRepository.findAll(pageable).map(this::mapToDTO);
    }

    @Override
    public Page<OrderDTO> getOrdersByUserId(Long userId, Pageable pageable) {
        log.info("Fetching orders for user id: {}", userId);
        return orderRepository.findByUserId(userId, pageable).map(this::mapToDTO);
    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(Long orderId) {
        log.info("Cancelling order id: {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

        order.setStatus(OrderStatus.CANCELLED);
        Order updatedOrder = orderRepository.save(order);
        return mapToDTO(updatedOrder);
    }

    private OrderDTO mapToDTO(Order order) {
        List<ProductDTO> productDTOs = order.getProducts().stream().map(product ->
                ProductDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .imageUrl(product.getImageUrl())
                        .brand(product.getBrand())
                        .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                        .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                        .build()
        ).collect(Collectors.toList());

        return OrderDTO.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .userName(order.getUser().getName())
                .products(productDTOs)
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus() != null ? order.getStatus().name() : null)
                .createdAt(order.getCreatedAt())
                .build();
    }
}
