package com.taha.springcrud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @Test
    void testCreateAndGetProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);

        Product savedProduct = productController.createProduct(product);
        assertEquals("Test Product", savedProduct.getName());
        assertEquals(100.0, savedProduct.getPrice());
    }
}
