package ru.gb.market.order.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.order.data.DeliveryType;
import ru.gb.market.order.data.PickUpPoint;
import ru.gb.market.order.dtos.CustomerDto;
import ru.gb.market.order.services.OrderService;
import ru.gb.market.order.wrappers.OrderWrapper;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderConrtoller {

    private final OrderService orderService;

    @GetMapping
    public CustomerDto getCustomer(@RequestParam String username) {
        return new CustomerDto(orderService.getCustomer(username));
    }

    @GetMapping("/delivery_types")
    public List<DeliveryType> getTypes() {
        return orderService.getTypes();
    }

    @GetMapping("/pickup_points")
    public List<PickUpPoint> getPickUpPoints() {
        return orderService.getPickUpPoints();
    }

    @PostMapping
    public void createOrder(@RequestHeader(name = "username", required = false) String username, @RequestBody OrderWrapper orderWrapper) {
        orderService.createOrder(orderWrapper, username);
    }
}
