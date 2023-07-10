package com.example.gccoffee.controller;

import com.example.gccoffee.model.CreateOrderRequest;
import com.example.gccoffee.model.Email;
import com.example.gccoffee.model.Order;
import com.example.gccoffee.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderRestController {

    private final OrderService orderService;

    @PostMapping("/v1/orders")
    public Order createOrder(@RequestBody CreateOrderRequest request) {
        Order order = orderService.createOrder(Email.builder().address(request.getEmail()).build(),
                request.getAddress(),
                request.getPostcode(),
                request.getOrderItems());

        return order;
    }
}
