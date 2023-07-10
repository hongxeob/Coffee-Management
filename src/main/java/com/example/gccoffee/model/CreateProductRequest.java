package com.example.gccoffee.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@Builder
public class CreateProductRequest {

    private final String productName;
    private final Category category;
    private final long price;
    private final String description;

}
