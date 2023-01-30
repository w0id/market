package ru.gb.market.order.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.gb.market.order.api.CartDto;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public CartDto getCurrentCart(String username) {
        return cartServiceWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/cart_items")
                        .queryParam("uuid", "0")
                        .build()
                )
                .header("username", username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

}
