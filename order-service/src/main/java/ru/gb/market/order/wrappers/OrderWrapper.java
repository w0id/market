package ru.gb.market.order.wrappers;

import lombok.Data;
import ru.gb.market.order.api.CartDto;
import ru.gb.market.order.data.Customer;
import ru.gb.market.order.data.DeliveryType;
import ru.gb.market.order.data.PickUpPoint;

@Data
public class OrderWrapper {

    private Customer customer;
    private DeliveryType deliveryType;
    private PickUpPoint pickUpPoint;

}
