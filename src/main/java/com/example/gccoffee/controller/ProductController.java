package com.example.gccoffee.controller;

import com.example.gccoffee.model.CreateProductRequest;
import com.example.gccoffee.model.Product;
import com.example.gccoffee.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public String products(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products/products";
    }


    @GetMapping("/products/new")
    public String newProductPage() {
        return "products/saveForm";
    }

    @PostMapping("/products")
    public String newProduct(CreateProductRequest request) {
        productService.createProduct(request.getProductName(),
                request.getCategory(),
                request.getPrice(),
                request.getDescription());

        return "redirect:/products";
    }
}
