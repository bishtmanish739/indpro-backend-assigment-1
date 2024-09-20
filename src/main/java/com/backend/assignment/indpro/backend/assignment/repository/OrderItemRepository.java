package com.backend.assignment.indpro.backend.assignment.repository;

import com.backend.assignment.indpro.backend.assignment.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}