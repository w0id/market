package ru.gb.market.order.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.order.data.PickUpPoint;
import ru.gb.market.order.repositories.IPickUpPointRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PickUpService {

    private final IPickUpPointRepository pickUpPointRepository;

    public List<PickUpPoint> getPickUpPoints() {
        return pickUpPointRepository.findAll();
    }
}
