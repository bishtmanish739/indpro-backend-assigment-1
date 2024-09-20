package com.backend.assignment.indpro.backend.assignment.service;

import com.backend.assignment.indpro.backend.assignment.entity.Product;
import com.backend.assignment.indpro.backend.assignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProductQuantity(Long id, Integer quantity) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setQuantity(quantity);
        return productRepository.save(product);
    }

    public BigDecimal getTotalInventoryValue() {
        return productRepository.findAll().stream()
            .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
