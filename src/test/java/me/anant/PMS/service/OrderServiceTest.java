package me.anant.PMS.service;

import me.anant.PMS.exceptions.ProductNotFoundException;
import me.anant.PMS.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.AssertionErrors;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Check data init for pre-saved data for orders which is 4 entries
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;


    @Test
    void testGet() {
        AssertionErrors.assertFalse("should not be empty since we save it in datainit", CollectionUtils.isEmpty(orderService.get()));
    }

    @Test
    void testDelete() {
        final Long id = Long.valueOf(2);

        orderService.delete(id);

        Assertions.assertThrows(ProductNotFoundException.class, () -> orderService.findById(id));
    }

    @Test
    void testSaveOrderAndFind() throws ProductNotFoundException {
        Order order = new Order();
        order.setCreateDateTime(LocalDateTime.now());

        orderService.save(order);

        AssertionErrors.assertNotNull("id should not be null", order.getId());

        Optional<Order> saveOrder = orderService.findById(order.getId());

        Assertions.assertEquals(order.getId(), saveOrder.get().getId());
    }

    @Test
    void testChangeStatus() throws ProductNotFoundException {
        final Long id = Long.valueOf(1);

        orderService.changeStatus(id, "CANCEL");
        Optional<Order> canceledOrder = orderService.findById(id);
        Assertions.assertEquals("CANCEL", canceledOrder.get().getStatus());
    }

    @Test
    void testCancelOrder() throws ProductNotFoundException {
        final Long id = Long.valueOf(1);

        AssertionErrors.assertFalse("it should return false", orderService.cancelOrder(id));
    }
}
