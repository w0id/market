package ru.gb.market.order.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import ru.gb.market.order.api.CartDto;
import ru.gb.market.order.api.UnauthorizedUserException;
import ru.gb.market.order.converters.CustomerConverter;
import ru.gb.market.order.converters.ProductConverter;
import ru.gb.market.order.data.*;
import ru.gb.market.order.events.OrderEvent;
import ru.gb.market.order.integrations.CartServiceIntegration;
import ru.gb.market.order.integrations.CustomerServiceIntegration;
import ru.gb.market.order.integrations.ProductServiceIntegration;
import ru.gb.market.order.repositories.IOrderRepository;
import ru.gb.market.order.wrappers.OrderWrapper;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerServiceIntegration customerServiceIntegration;
    private final ProductServiceIntegration productServiceIntegration;
    private final CartServiceIntegration cartServiceIntegration;
    private final CustomerConverter customerConverter;

    private final ProductConverter productConverter;
    private final DeliveryService deliveryService;
    private final PickUpService pickUpService;
    private final IOrderRepository orderRepository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public Customer getCustomer(String username, String token) {
        try {
            return customerConverter.dtoToEntity(customerServiceIntegration.getCustomerByUsername(username, token));
        } catch (UnauthorizedUserException e) {
            throw new UnauthorizedUserException("Пользователь не авторизован");
        }
    }

    public List<DeliveryType> getTypes() {
        return deliveryService.getTypes();
    }

    public List<PickUpPoint> getPickUpPoints() {
        return pickUpService.getPickUpPoints();
    }

    @Transactional
    public void createOrder(final OrderWrapper orderWrapper, String username) {
        Order order = new Order();
        if (null != orderWrapper.getPickUpPoint().getId()) {
            order.setPickUpPoint(orderWrapper.getPickUpPoint());
        }
        order.setCustomer(orderWrapper.getCustomer());
        order.setDeliveryType(orderWrapper.getDeliveryType());
        CartDto cartDto = cartServiceIntegration.getCurrentCart(username);
        order.setOrderItems(cartDto.getItems().stream().map(
                        cartItem -> {
                            return new OrderItem(
                                    productConverter.dtoToEntity(productServiceIntegration.getProductById(cartItem.getId())),
                                    order,
                                    cartItem.getQuantity()
                            );
                        }
                ).collect(Collectors.toList())
        );
        OrderEvent orderEvent = new OrderEvent(this, orderWrapper, cartDto, username);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                applicationEventPublisher.publishEvent(orderEvent);
            }
        });
        orderRepository.save(order);
    }
}
