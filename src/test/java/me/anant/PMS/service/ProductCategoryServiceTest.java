package me.anant.PMS.service;

import me.anant.PMS.model.ProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ProductCategoryServiceTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    @Order(1)
    void testGet() {
        List<ProductCategory> productCategoryList = productCategoryService.get();

        Assertions.assertFalse(CollectionUtils.isEmpty(productCategoryList));
    }

    @Test
    @Order(2)
    void testSave() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("category-a");

        productCategoryService.save(productCategory);
    }

    @Test
    @Order(3)
    void testFindById() {
        // category-a id is 204
        Optional<ProductCategory> productCategory = productCategoryService.findById(Long.valueOf(204));

        Assertions.assertEquals(Long.valueOf(204), productCategory.get().getId());
    }

    @Test
    @Order(4)
    void testDelete() {

        productCategoryService.delete(Long.valueOf(204));

        Optional<ProductCategory> productCategory = productCategoryService.findById(Long.valueOf(204));

        Assertions.assertFalse(productCategory.isPresent());
    }


}
