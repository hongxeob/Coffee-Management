package com.example.gccoffee.repository;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.example.gccoffee.Utils.toLocalDateTime;

@Repository
public class ProductJdbcRepository implements ProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private static final String SAVE = "insert into products(product_id, product_name, category, price, description, created_at, updated_at) VALUES(:productId, :productName, :category, :price, :description, :createdAt, :updatedAt)";
    private static final String FIND_ALL = "select * from products";
    private static final String FIND_BY_ID = "select * from products where product_id = :productId";
    private static final String FIND_BY_NAME = "select * from products where product_name = :productName";
    private static final String FIND_BY_CATEGORY = "select * from products where category = :category";
    private static final String DELETE_ALL = "delete from products";
    private static final String UPDATE = "update products set product_name = :productName, category = :category, price = :price, description = :description, created_at = :createdAt, updated_at = :updatedAt " +
            "where product_id = :productId";

    public ProductJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(FIND_ALL, productRowMapper);
    }

    @Override
    public Product save(Product product) {

        int update = jdbcTemplate.update(SAVE, toParam(product));

        if (update != 1) {
            throw new RuntimeException("저장할 수 없습니다.");
        }
        return product;
    }

    @Override
    public Optional<Product> findById(UUID productId) {

        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(FIND_BY_ID,
                            Collections.singletonMap("productId", productId), productRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Product> findByName(String productName) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(FIND_BY_NAME,
                            Collections.singletonMap("productName", productName), productRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return jdbcTemplate.query(FIND_BY_CATEGORY,
                Collections.singletonMap("category", category.toString()), productRowMapper);
    }

    @Override
    public Product update(Product product) {
        int update = jdbcTemplate.update(UPDATE, toParam(product));

        if (update != 1) {
            throw new RuntimeException("업데이트에 실패했습니다.");
        }

        return product;
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update(DELETE_ALL, Collections.emptyMap());
    }

    private static final RowMapper<Product> productRowMapper = (rs, rowNum) -> {
        String productId = rs.getString("product_id");
        String productName = rs.getString("product_name");
        Category category = Category.valueOf(rs.getString("category"));
        long price = rs.getLong("price");
        String description = rs.getString("description");
        LocalDateTime createdAt = toLocalDateTime(rs.getTimestamp("created_at"));
        LocalDateTime updatedAt = toLocalDateTime(rs.getTimestamp("updated_at"));

        return Product.builder()
                .productId(UUID.fromString(productId))
                .productName(productName)
                .category(category)
                .description(description)
                .price(price)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    };

    private Map<String, Object> toParam(Product product) {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("productId", product.getProductId().toString());
        paramMap.put("productName", product.getProductName());
        paramMap.put("category", product.getCategory().toString());
        paramMap.put("price", product.getPrice());
        paramMap.put("description", product.getDescription());
        paramMap.put("createdAt", product.getCreatedAt());
        paramMap.put("updatedAt", product.getUpdatedAt());
        return paramMap;
    }

}
