package com.backend.assignment.indpro.backend.assignment.controller;

import com.backend.assignment.indpro.backend.assignment.entity.Product;
import com.backend.assignment.indpro.backend.assignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductQuantity(@PathVariable Long id, @RequestBody Product product) {
        int quantity=product.getQuantity();
        return ResponseEntity.ok(productService.updateProductQuantity(id, quantity));
    }

    @GetMapping("/inventory/value")
    public ResponseEntity<Map<String, BigDecimal>> getTotalInventoryValue() {
        BigDecimal totalValue = productService.getTotalInventoryValue();
        return ResponseEntity.ok(Map.of("total_inventory_value", totalValue));
    }
}
