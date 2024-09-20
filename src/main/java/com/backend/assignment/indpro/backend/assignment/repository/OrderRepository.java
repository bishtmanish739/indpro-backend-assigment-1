package com.backend.assignment.indpro.backend.assignment.repository;

import com.backend.assignment.indpro.backend.assignment.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}