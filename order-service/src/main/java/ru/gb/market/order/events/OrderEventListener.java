package ru.gb.market.order.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.gb.market.order.api.CartDto;

import java.util.stream.Collectors;

@Slf4j
@EnableAsync
@Configuration
public class OrderEventListener {

    /**
     * Имитация асинхронной отправки email уведомления при успешном размещении заказа
     */

    @Async
    @EventListener
    public void onOrderEvent(final OrderEvent event) {
        CartDto cartDto = event.getCartDto();
        String items = cartDto.getItems().stream().map(i -> String.format("%s x %s", i.getProductName(), String.valueOf(i.getQuantity()))).collect(Collectors.joining("\n"));
        String totalCost = cartDto.getTotalCost().toString();
        String type = event.getOrder().getDeliveryType().toString();
        String message = String.format("\n Уважаемый %s! \n\n Ваш заказ: \n %s \n на сумму %sр. принят. \n\n Спососб доставки: %s", event.getUsername(), items, totalCost, type);
        log.info(message);
    }
}
