package ru.gb.market.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.order.data.DeliveryType;

public interface IDeliveryTypeRepository extends JpaRepository<DeliveryType, Long> {
}