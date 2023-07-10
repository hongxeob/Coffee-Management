package com.example.gccoffee.service;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import com.example.gccoffee.repository.ProductJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductJdbcRepository productRepository;

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String productName, Category category, long price) {
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .productName(productName)
                .category(category)
                .price(price)
                .build();

        return productRepository.save(product);
    }

    @Override
    public Product createProduct(String productName, Category category, long price, String description) {
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .productName(productName)
                .category(category)
                .price(price)
                .description(description)
                .build();

        return productRepository.save(product);
    }
}
