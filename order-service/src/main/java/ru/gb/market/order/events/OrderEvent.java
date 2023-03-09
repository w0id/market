package ru.gb.market.order.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.gb.market.order.api.CartDto;
import ru.gb.market.order.views.OrderView;

@Getter
public class OrderEvent extends ApplicationEvent {

    private String username;
    private OrderView order;
    private CartDto cartDto;

    public OrderEvent(final Object source, final OrderView order, final CartDto cartDto, String username) {
        super(source);
        this.order = order;
        this.username = username;
        this.cartDto = cartDto;
    }
}
