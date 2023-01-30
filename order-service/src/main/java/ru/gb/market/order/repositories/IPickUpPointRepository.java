package ru.gb.market.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.order.data.PickUpPoint;

public interface IPickUpPointRepository extends JpaRepository<PickUpPoint, Long> {
}