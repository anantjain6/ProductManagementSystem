package me.anant.PMS.service;

import me.anant.PMS.exceptions.ProductNotFoundException;
import me.anant.PMS.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.AssertionErrors;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @DisplayName("It should find a product")
    @Test
    void testFindById() throws ProductNotFoundException {

        // since we determine the presaved data ang its id is 105
        Optional<Product> actual = productService.findById(Long.valueOf(105));

        Assertions.assertEquals("shabu", actual.get().getProductName());
    }

    @DisplayName("It should return an exception since its not existing")
    @Test
    void testFindByIdNotFound() throws ProductNotFoundException {
        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.findById(Long.valueOf(888)));
    }

    @DisplayName("It should add product qty")
    @Test
    void testAddQty() throws ProductNotFoundException {
        productService.addQty(105, 1);
    }

    @DisplayName("It should deduct product qty")
    @Test
    void testDeductQty() throws ProductNotFoundException {
        productService.deductQty(105, 1);
    }

    @DisplayName("It should save a new Product and delete again. To confirm query and it should throw exception  ")
    @Test
    void testSaveAndDelete() {
        Product product = new Product();
        product.setProductName("bolitas");

        productService.save(product);

        AssertionErrors.assertNotNull("Id should not be null since h2 will provide it", product.getProductId());

        productService.delete(product.getProductId());

        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.findById(Long.valueOf(product.getProductId())));

    }

    @Test
    void testGet() {
        AssertionErrors.assertFalse("It should have data since we provide it in the initialization", CollectionUtils.isEmpty(productService.get()));
    }

}
