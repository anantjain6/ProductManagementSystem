package me.anant.PMS.service;

import me.anant.PMS.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @DisplayName("It should save a product")
    @Test
    void testSave() {

        // since we determine the presaved data ang its id is 105
        Optional<Product> actual = productService.findById(Long.valueOf(105));

        Assertions.assertEquals("shabu", actual.get().getProductName());
    }
}
