package me.anant.PMS.init;

import me.anant.PMS.dao.OrderRepository;
import me.anant.PMS.dao.ProductCategoryRepository;
import me.anant.PMS.dao.ProductRepository;
import me.anant.PMS.dao.UserRepository;
import me.anant.PMS.model.Order;
import me.anant.PMS.model.OrderProduct;
import me.anant.PMS.model.Product;
import me.anant.PMS.model.ProductCategory;
import me.anant.PMS.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class DataInit {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @PostConstruct
    public void init() {
        User user = new User();
        user.setEmail("a@a.com");
        user.setName("nyor ja");
        user.setRole("ROLE_ADMIN");
        user.setPassword("password");

        userRepository.save(user);

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("drugs");

        productCategoryRepository.save(productCategory);

        Product product = new Product();
        product.setProductName("shabu");
        product.setProductPrice(1f);
        product.setProductQty(1);

        product.setCategory(productCategory);

        productRepository.save(product);


        for (int i = 0; i < 4; i++) {
            Order order = new Order();
            order.setCreateDateTime(LocalDateTime.now());
            orderRepository.save(order);
        }


        Product product2 = new Product();
        product2.setProductName("maryjane");
        product2.setProductPrice(1f);
        product2.setProductQty(1);

        product2.setCategory(productCategory);

        productRepository.save(product2);

    }
}
