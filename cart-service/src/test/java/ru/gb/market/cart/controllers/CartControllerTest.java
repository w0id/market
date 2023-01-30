package ru.gb.market.cart.controllers;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import ru.gb.market.cart.api.CartDto;
import ru.gb.market.cart.api.ProductDto;
import ru.gb.market.cart.integrations.ProductServiceIntegration;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CartControllerTest {

    @Autowired
    WebTestClient webTestClient;
    @MockBean
    ProductServiceIntegration productServiceIntegration;

    void mockProduct() {
        Mockito.when(productServiceIntegration.getProductById(1L)).thenReturn(new ProductDto(1L, "TestProduct", new BigDecimal(1)));
    }

    @Test
    @Order(1)
    void getCartItems() {
        CartDto cart = webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/cart_items")
                        .queryParam("uuid", "0")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(CartDto.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertNotNull(cart);
        Assertions.assertNotNull(cart.getItems());
    }

    @Test
    @Order(2)
    void addToCart() {
        mockProduct();
        webTestClient.post()
                .uri("/api/v1/cart_items")
                .body(BodyInserters.fromFormData("uuid", "0")
                        .with("id", "1"))
                .exchange()
                .expectStatus().isOk();
        CartDto cart = webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/cart_items")
                        .queryParam("uuid", "0")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(CartDto.class)
                .returnResult()
                .getResponseBody();
        cart.getItems().stream().forEach(i -> {
            Assertions.assertEquals(i.getId(), 1L);
            Assertions.assertEquals(i.getProductName(), "TestProduct");
            Assertions.assertEquals(i.getQuantity(), 1);
            Assertions.assertEquals(i.getCost(), new BigDecimal(1));
            Assertions.assertEquals(i.getCostPerProduct(), new BigDecimal(1));
        });
        Assertions.assertEquals(cart.getTotalCost(), new BigDecimal(1));
    }

    @Test
    @Order(3)
    void deleteFromCart() {
        mockProduct();
        webTestClient.post()
                .uri("/api/v1/cart_items")
                .body(BodyInserters.fromFormData("uuid","0")
                        .with("id", "1"))
                .exchange()
                .expectStatus().isOk();
        webTestClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/cart_items")
                        .queryParam("uuid", "0")
                        .queryParam("id",1)
                        .build())
                .exchange()
                .expectStatus().isOk();
        CartDto cart = webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/cart_items")
                        .queryParam("uuid", "0")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(CartDto.class)
                .returnResult()
                .getResponseBody();
        Assertions.assertNotNull(cart);
        Assertions.assertEquals(new BigDecimal(0), cart.getTotalCost());
        Assertions.assertEquals(new ArrayList<>(),cart.getItems());
    }

    @Test
    @Order(4)
    void changeQuantity() {
        deleteFromCart();
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.set("uuid", "0");
        body.add("id", "1");
        body.add("quantity", "1");

        mockProduct();
        webTestClient.post()
                .uri("/api/v1/cart_items")
                .body(BodyInserters.fromFormData("uuid","0")
                        .with("id", "1"))
                .exchange()
                .expectStatus().isOk();
        webTestClient.put()
                .uri("/api/v1/cart_items")
                .body(BodyInserters.fromFormData(body))
                .exchange()
                .expectStatus().isOk();
        CartDto cart = webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/cart_items")
                        .queryParam("uuid", "0")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(CartDto.class)
                .returnResult()
                .getResponseBody();
        cart.getItems().stream().forEach(i -> {
            Assertions.assertEquals(i.getId(), 1L);
            Assertions.assertEquals(i.getProductName(), "TestProduct");
            Assertions.assertEquals(i.getQuantity(), 2);
            Assertions.assertEquals(i.getCost(), new BigDecimal(2));
            Assertions.assertEquals(i.getCostPerProduct(), new BigDecimal(1));
        });
        Assertions.assertEquals(cart.getTotalCost(), new BigDecimal(2));
        deleteFromCart();
    }
}