package ru.gb.market.order.mappers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market.order.converters.ProductConverter;
import ru.gb.market.order.data.Order;
import ru.gb.market.order.data.OrderItem;
import ru.gb.market.order.integrations.CartServiceIntegration;
import ru.gb.market.order.integrations.ProductServiceIntegration;
import ru.gb.market.order.views.OrderView;

import java.util.stream.Collectors;

@Component
@Data
@RequiredArgsConstructor
public class OrderMapper {

    private final CartServiceIntegration cartServiceIntegration;
    private final ProductConverter productConverter;
    private final ProductServiceIntegration productServiceIntegration;

    public Order orderWrapperToOrder(OrderView wrapper, String username) {
        Order order = Order.builder()
                .customer(wrapper.getCustomer())
                .deliveryType(wrapper.getDeliveryType())
                .pickUpPoint(wrapper.getPickUpPoint().getId() != null ? wrapper.getPickUpPoint() : null)
                .build();
        order.setOrderItems(cartServiceIntegration.getCurrentCart(username).getItems().stream().map(
                cartItem -> {
                    return new OrderItem(
                            productConverter.dtoToEntity(productServiceIntegration.getProductById(cartItem.getId())),
                            order,
                            cartItem.getQuantity()
                    );
                }
        ).collect(Collectors.toList()));
        return order;
    }
}
