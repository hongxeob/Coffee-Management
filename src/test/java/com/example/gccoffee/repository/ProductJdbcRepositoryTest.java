package com.example.gccoffee.repository;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Transactional
@Import(ProductJdbcRepository.class)
@ActiveProfiles("test")
class ProductJdbcRepositoryTest {

    @Autowired
    ProductJdbcRepository repository;

    @Test
    @DisplayName("상품 생성")
    void productSaveTest() throws Exception {

        //given
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .productName("asdf")
                .price(10)
                .category(Category.COFFEE_BEAN_PACKAGE)
                .build();
        //when
        repository.save(product);

        List<Product> products = repository.findAll();

        //then
        assertThat(products.isEmpty()).isEqualTo(false);
    }

    @Test
    @DisplayName("상품ID로 조회")
    void findByIdTest() throws Exception {

        //given
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .productName("asdf")
                .price(10)
                .category(Category.COFFEE_BEAN_PACKAGE)
                .build();
        repository.save(product);

        //when
        Optional<Product> byId = repository.findById(product.getProductId());

        //then
        assertThat(byId.isPresent()).isEqualTo(true);
        assertThat(byId.get().getProductId()).isEqualTo(product.getProductId());
    }

    @Test
    @DisplayName("상품 이름으로 조회")
    void findByTypeTest() throws Exception {

        //given
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .productName("asdf")
                .price(10)
                .category(Category.COFFEE_BEAN_PACKAGE)
                .build();
        repository.save(product);

        //when
        Optional<Product> foundProduct = repository.findByName(product.getProductName());

        //then
        assertThat(foundProduct.isPresent()).isEqualTo(true);
        assertThat(foundProduct.get().getProductName()).isEqualTo(product.getProductName());
    }

    @Test
    @DisplayName("상품 이름으로 조회")
    void findByCategoryTest() throws Exception {

        //given
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .productName("asdf")
                .price(10)
                .category(Category.COFFEE_BEAN_PACKAGE)
                .build();
        repository.save(product);

        //when
        List<Product> foundProducts = repository.findByCategory(product.getCategory());

        //then
        assertThat(foundProducts.get(0).getCategory()).isEqualTo(product.getCategory());
    }

    @Test
    @DisplayName("전체 삭제 성공")
    void deleteAllTest() throws Exception {

        //given
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .productName("asdf")
                .price(10)
                .category(Category.COFFEE_BEAN_PACKAGE)
                .build();
        repository.save(product);

        //when
        repository.deleteAll();
        List<Product> products = repository.findAll();

        //then
        assertThat(products.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("업데이트 성공")
    void updateSuccessTest() throws Exception {

        //given
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .productName("asdf")
                .price(10)
                .category(Category.COFFEE_BEAN_PACKAGE)
                .build();
        repository.save(product);
        product.updateProductName("aaaaa");

        //when
        Product updatedProduct = repository.update(product);

        //then
        assertThat(updatedProduct.getProductName()).isEqualTo("aaaaa");
    }
}