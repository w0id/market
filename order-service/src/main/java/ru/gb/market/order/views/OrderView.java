package ru.gb.market.order.views;

import lombok.Data;
import ru.gb.market.order.data.Customer;
import ru.gb.market.order.data.DeliveryType;
import ru.gb.market.order.data.PickUpPoint;

@Data
public class OrderView {

    private Customer customer;
    private DeliveryType deliveryType;
    private PickUpPoint pickUpPoint;

}
