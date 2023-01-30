package ru.gb.market.order.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.order.data.DeliveryType;
import ru.gb.market.order.repositories.IDeliveryTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final IDeliveryTypeRepository deliveryTypeRepository;

    public List<DeliveryType> getTypes() {
        return deliveryTypeRepository.findAll();
    }
}
