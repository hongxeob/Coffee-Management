package com.example.gccoffee.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {

    private UUID orderId;
    private Email email;
    private String address;
    private String postcode;
    private List<OrderItem> oderItems;
    private OrderStatus orderStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Order(UUID orderId, Email email, String address, String postcode, List<OrderItem> oderItems, OrderStatus orderStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.oderItems = oderItems;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void updateAddress(String address) {
        this.address = address;
        this.updatedAt = LocalDateTime.now();

    }

    public void updatePostcode(String postcode) {
        this.postcode = postcode;
        this.updatedAt = LocalDateTime.now();

    }

    public void updateOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        this.updatedAt = LocalDateTime.now();

    }


}
