package com.example.gccoffee.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CreateOrderRequest {

    private String email;
    private String address;
    private String postcode;
    private List<OrderItem> orderItems;


    @Builder
    public CreateOrderRequest(String email, String address, String postcode, List<OrderItem> orderItems) {
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderItems = orderItems;
    }
}
