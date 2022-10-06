package me.anant.PMS.service;

import me.anant.PMS.dao.ProductCategoryRepository;
import me.anant.PMS.dao.ProductRepository;
import me.anant.PMS.exceptions.ProductNotFoundException;
import me.anant.PMS.model.Product;
import me.anant.PMS.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
final class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @BeforeEach
    void setUp() {
        // clear database;
        productRepository.deleteAll();
        productCategoryRepository.deleteAll();
    }

    @Test
    void noProductFound() {
        final Optional<Product> productOptional = productRepository.findById(1L);
        assertFalse(productOptional.isPresent());
    }

    @Test
    void savingProduct() {
        final ProductCategory category = new ProductCategory("test");
        productCategoryRepository.save(category);
        final Product product = new Product("test", 1.0f, 1, category);

        productService.save(product);

        final long id = product.getProductId();

        final Optional<Product> savedProductOptional = productRepository.findById(id);
        assertTrue(savedProductOptional.isPresent());
    }

    @Test
    void deletingProduct() {
        final ProductCategory category = new ProductCategory("test");
        productCategoryRepository.save(category);
        final Product product = new Product("test", 1.0f, 1, category);
        productService.save(product);
        final long id = product.getProductId();

        productService.delete(id);

        final Optional<Product> productOptional = productRepository.findById(id);
        assertFalse(productOptional.isPresent());
    }

    @Test
    void gettingListOfProducts() {
        final ProductCategory category = new ProductCategory("test");
        productCategoryRepository.save(category);
        productService.save(new Product("test", 1.0f, 1, category));
        productService.save(new Product("test1", 1.0f, 1, category));
        productService.save(new Product("test2", 1.0f, 1, category));

        List<Product> productList = productService.get();

        assertEquals(3, productList.size());
    }

    @Test
    void updateProductDoesNotExist() {
        final ProductCategory category = new ProductCategory("test");
        productCategoryRepository.save(category);
        assertThrows(ProductNotFoundException.class, ()-> {
            productService.updateProduct(new Product("test", 1.0f, 1, category));
        });
    }

    @Test
    void updateProduct() throws ProductNotFoundException {
        final ProductCategory category = new ProductCategory("test");
        productCategoryRepository.save(category);
        final Product product = productService.save(new Product("test", 1.0f, 1, category));

        product.setProductQty(5);
        product.setProductPrice(2.0f);
        productService.updateProduct(product);

        final Optional<Product> optional =productRepository.findById(product.getProductId());
        assertTrue(optional.isPresent());
        final Product productUpdated = optional.get();
        assertEquals(5, productUpdated.getProductQty());
        assertEquals(2.0f, productUpdated.getProductPrice());
    }
}