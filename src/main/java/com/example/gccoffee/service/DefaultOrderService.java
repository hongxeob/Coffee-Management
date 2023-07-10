package com.example.gccoffee.service;

import com.example.gccoffee.model.Email;
import com.example.gccoffee.model.Order;
import com.example.gccoffee.model.OrderItem;
import com.example.gccoffee.model.OrderStatus;
import com.example.gccoffee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(Email email, String address, String postcode, List<OrderItem> oderItems) {
        Order order = Order.builder().orderId(UUID.randomUUID())
                .email(email)
                .address(address)
                .postcode(postcode)
                .oderItems(oderItems)
                .orderStatus(OrderStatus.ACCEPTED)
                .createdAt(now())
                .updatedAt(now())
                .build();

        return orderRepository.save(order);
    }
}
