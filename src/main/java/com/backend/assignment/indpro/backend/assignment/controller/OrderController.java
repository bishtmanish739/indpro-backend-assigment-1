package com.backend.assignment.indpro.backend.assignment.controller;


import com.backend.assignment.indpro.backend.assignment.dto.OrderDTO;
import com.backend.assignment.indpro.backend.assignment.dto.OrderItemDTO;
import com.backend.assignment.indpro.backend.assignment.entity.Order;
import com.backend.assignment.indpro.backend.assignment.entity.OrderItem;
import com.backend.assignment.indpro.backend.assignment.entity.Product;
import com.backend.assignment.indpro.backend.assignment.entity.User;
import com.backend.assignment.indpro.backend.assignment.repository.OrderItemRepository;
import com.backend.assignment.indpro.backend.assignment.repository.OrderRepository;
import com.backend.assignment.indpro.backend.assignment.repository.ProductRepository;
import com.backend.assignment.indpro.backend.assignment.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    // POST /orders - Create a new order
    @PostMapping("")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        BigDecimal totalPrice= BigDecimal.valueOf(0);
        // Assuming the user ID is passed in the orderDTO
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create the order and set the user
        Order order = new Order();
        order.setUser(user);


        // Save order items and set the relationship
        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            Product product =  productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setPrice(itemDTO.getPrice());
            orderItem.setOrder(order); // Set the order reference
            order.getOrderItems().add(orderItem);
            totalPrice=totalPrice.add( product.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity())));
        }

        // Save the order (this should cascade to save order items as well)
        order.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }
    // POST /orders/{id}/items - Add items to an order
    @PostMapping("/{id}/items")
    public OrderItem addItemToOrder(@PathVariable Long id, @RequestBody OrderItemDTO itemDTO) {
        // Find the order by ID
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Find the product by ID
        Product product = productRepository.findById(itemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Create and set up the order item
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product); // Set the product here
        orderItem.setQuantity(itemDTO.getQuantity());
        orderItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()))); // Calculate price based on quantity

        // Save the order item
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        // Update the total price of the order
        BigDecimal totalPrice = order.getTotalPrice().add(product.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
        order.setTotalPrice(totalPrice);
        orderRepository.save(order); // Update the order

        return savedOrderItem;
    }


    // GET /orders/{id}/total - Get the total price of an order
    @GetMapping("/{id}/total")
    public BigDecimal getTotalOrderPrice(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return order.getTotalPrice();
    }

    @GetMapping("/{id}")
    public Order getOrders(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return order;
    }
}
