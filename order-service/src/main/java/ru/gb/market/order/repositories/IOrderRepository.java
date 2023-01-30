package ru.gb.market.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.order.data.Order;

public interface IOrderRepository extends JpaRepository<Order, Long> {
}