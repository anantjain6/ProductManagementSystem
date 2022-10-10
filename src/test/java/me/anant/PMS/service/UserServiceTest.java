package me.anant.PMS.service;

import me.anant.PMS.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testFindEmail() {
        User user = userService.findByEmail("a@a.com");
        Assertions.assertEquals("a@a.com", user.getEmail());
    }
}
