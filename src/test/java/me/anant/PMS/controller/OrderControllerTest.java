package me.anant.PMS.controller;

import me.anant.PMS.dao.ProductRepository;
import me.anant.PMS.service.EmailService;
import me.anant.PMS.service.OrderService;
import me.anant.PMS.service.ProductService;
import me.anant.PMS.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.support.TransactionTemplate;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class OrderControllerTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private Principal principal;
    @Autowired
    private ProductService productService;
    @Mock
    private UserService userService;
    @Autowired
    OrderService orderService;
    @Mock
    EmailService emailService;

    @MockBean
    OrderController orderController;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    TransactionTemplate transactionTemplate;

    @BeforeEach
    void setup(){
        orderController = new OrderController(productService, userService, orderService, emailService, transactionTemplate);
    }
    @Test
    public void whenPlacedOrderIsSuccessDeductQty(){
        String[] testPIds = {"101"};
        String testEmail = "customer@gmail.com";
        Integer testQty = 1;
        Mockito.when(request.getParameterValues("productId")).thenReturn(testPIds);
        Mockito.when(request.getParameter(Mockito.any())).thenReturn(testQty.toString());
        Mockito.when(principal.getName()).thenReturn(testEmail);
        Mockito.doNothing().when(emailService).send(Mockito.anyString(), Mockito.anyString(), Mockito.anyString() );
        Integer beforeSaveQty = productRepository.findById(101L).get().getProductQty();
        Integer beforeSaveOrders = orderService.get().size();

        orderController.orderPlace(request, principal);

        Integer afterSaveQty = productRepository.findById(101L).get().getProductQty();
        Integer afterSaveOrders = orderService.get().size();

        assertEquals(testQty,  beforeSaveQty - afterSaveQty);
        assertEquals(testQty,  afterSaveOrders - beforeSaveOrders);

    }

    @Test
    public void whenPlacedOrderIsFailureDontDeductQty(){
        String[] testPIds = {"101"};
        String testEmail = "customer@gmail.com";
        Integer testQty = 1;
        Mockito.when(request.getParameterValues("productId")).thenReturn(testPIds);
        Mockito.when(request.getParameter(Mockito.any())).thenReturn(testQty.toString());
        Mockito.when(principal.getName()).thenReturn(testEmail);
        doThrow(new RuntimeException()).when(emailService).send(Mockito.anyString(), Mockito.anyString(), Mockito.anyString());


        Integer beforeSaveQty = productRepository.findById(101L).get().getProductQty();
        Integer beforeSaveOrders = orderService.get().size();
        try{
            orderController.orderPlace(request, principal);
        }catch (RuntimeException exception){
            System.out.println(exception);
        }

        Integer afterSaveQty = productRepository.findById(101L).get().getProductQty();
        Integer afterSaveOrders = orderService.get().size();

        assertEquals(beforeSaveQty,  afterSaveQty);
        assertEquals(beforeSaveOrders,afterSaveOrders);

    }

}