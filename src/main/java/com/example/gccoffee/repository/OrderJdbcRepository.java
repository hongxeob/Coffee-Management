package com.example.gccoffee.repository;

import com.example.gccoffee.model.Order;
import com.example.gccoffee.model.OrderItem;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class OrderJdbcRepository implements OrderRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private static final String SAVE = "insert into orders(order_id, email, address, postcode, order_status, created_at, updated_at) VALUES(:orderId, :email, :address, :postcode, :orderStatus, :createdAt, :updatedAt)";
    private static final String ORDER_ITEM_SAVE = "INSERT INTO order_items(order_id, product_id, category, price, quantity, created_at, updated_at) VALUES(:orderId, :productId, :category, :price, :quantity, :createdAt, :updatedAt)";

    public OrderJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    @Override
    public Order save(Order order) {
        int update = jdbcTemplate.update(SAVE, toOrderParamMap(order));

        if (update != 1) {
            throw new RuntimeException("저장할 수 없습니다.");
        }
        order.getOderItems().forEach(orderItem -> {

            jdbcTemplate.update(ORDER_ITEM_SAVE,
                    toOrderItemParamMap(order.getOrderId(), order.getCreatedAt(), order.getUpdatedAt(),
                            orderItem));
        });

        return order;
    }

    private Map<String, Object> toOrderParamMap(Order order) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderId", order.getOrderId().toString());
        paramMap.put("email", order.getEmail().toString());
        paramMap.put("address", order.getAddress());
        paramMap.put("postcode", order.getPostcode());
        paramMap.put("orderStatus", order.getOrderStatus().toString());
        paramMap.put("createdAt", order.getCreatedAt());
        paramMap.put("updatedAt", order.getUpdatedAt());

        return paramMap;
    }

    private Map<String, Object> toOrderItemParamMap(UUID orderId, LocalDateTime createdAt, LocalDateTime updatedAt, OrderItem item) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderId", orderId.toString());
        paramMap.put("productId", item.getProductId().toString());
        paramMap.put("category", item.getCategory().toString());
        paramMap.put("price", item.getPrice());
        paramMap.put("quantity", item.getQuantity());
        paramMap.put("createdAt", createdAt);
        paramMap.put("updatedAt", updatedAt);
        return paramMap;

    }

}