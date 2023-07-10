package com.example.gccoffee.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    private UUID productId;
    private Category category;
    private long price;
    private int quantity;

    @Builder
    public OrderItem(UUID productId, Category category, long price, int quantity) {
        this.productId = productId;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
}
