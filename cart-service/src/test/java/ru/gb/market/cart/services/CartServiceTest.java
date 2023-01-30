package ru.gb.market.cart.services;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.market.cart.api.ProductDto;
import ru.gb.market.cart.integrations.ProductServiceIntegration;
import ru.gb.market.cart.model.Cart;

import java.math.BigDecimal;
import java.util.ArrayList;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartServiceTest {

    @Autowired
    CartService cartService;
    @MockBean
    ProductServiceIntegration productServiceIntegration;

    void mockProduct() {
        Mockito.when(productServiceIntegration.getProductById(1L)).thenReturn(new ProductDto(1L, "TestProduct", new BigDecimal(1)));
    }


    @Test
    @Order(1)
    void getCurrentCart() {
        Cart currentCart = cartService.getCurrentCart("0");
        Assertions.assertNotNull(currentCart);
        Assertions.assertNotNull(currentCart.getItems());
    }

    @Test
    @Order(2)
    void add() {
        mockProduct();
        cartService.add("0", 1L);
        Cart currentCart = cartService.getCurrentCart("0");
        Assertions.assertNotNull(currentCart);
        Assertions.assertNotNull(currentCart.getItems());
        cartService.getCurrentCart("0").getItems().stream().forEach(i -> {
            Assertions.assertEquals(i.getId(), 1L);
            Assertions.assertEquals(i.getProductName(), "TestProduct");
            Assertions.assertEquals(i.getQuantity(), 1);
            Assertions.assertEquals(i.getCost(), new BigDecimal(1));
            Assertions.assertEquals(i.getCostPerProduct(), new BigDecimal(1));
        });
        Assertions.assertEquals(currentCart.getTotalCost(), new BigDecimal(1));
    }

    @Test
    @Order(3)
    void delete() {
        mockProduct();
        cartService.add("0", 1L);
        cartService.delete("0", 1L);
        Cart currentCart = cartService.getCurrentCart("0");
        Assertions.assertNotNull(currentCart);
        Assertions.assertEquals(new BigDecimal(0), currentCart.getTotalCost());
        Assertions.assertEquals(new ArrayList<>(),currentCart.getItems());
    }

    @Test
    @Order(4)
    void changeQuantity() {
        delete();
        mockProduct();
        cartService.add("0",1L);
        cartService.changeQuantity("0", 1L, 1);
        Cart currentCart = cartService.getCurrentCart("0");
        Assertions.assertNotNull(currentCart);
        Assertions.assertNotNull(currentCart.getItems());
        cartService.getCurrentCart("0").getItems().stream().forEach(i -> {
            Assertions.assertEquals(i.getId(), 1L);
            Assertions.assertEquals(i.getProductName(), "TestProduct");
            Assertions.assertEquals(i.getQuantity(), 2);
            Assertions.assertEquals(i.getCost(), new BigDecimal(2));
            Assertions.assertEquals(i.getCostPerProduct(), new BigDecimal(1));
        });
        Assertions.assertEquals(currentCart.getTotalCost(), new BigDecimal(2));
        delete();
    }
}