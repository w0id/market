package ru.gb.market.order.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.gb.market.order.api.ResourceNotFoundException;
import ru.gb.market.order.dtos.CustomerDto;

@Component
@RequiredArgsConstructor
public class CustomerServiceIntegration {
    private final WebClient customerServiceWebClient;

    public CustomerDto getCustomerByUsername(String username, String token) {
        return customerServiceWebClient.get()
//                .uri("/api/v1/customers/" + username)
                .uri("/api/v1/customers")
                .headers(headers -> {
                    headers.setBearerAuth(token.substring(7));
                    headers.set("username", username);
                })
                .retrieve()
                .onStatus(
                        httpStatusCode -> httpStatusCode.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Пользователь не найден в пользовательском МС"))
                )
                .bodyToMono(CustomerDto.class)
                .block();
    }

}
