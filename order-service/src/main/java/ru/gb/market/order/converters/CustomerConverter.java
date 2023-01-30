package ru.gb.market.order.converters;

import org.springframework.stereotype.Component;
import ru.gb.market.order.data.Customer;
import ru.gb.market.order.dtos.CustomerDto;

@Component
public class CustomerConverter {

    public Customer dtoToEntity(CustomerDto customerDto) {
        return new Customer(
                customerDto.getId(),
                customerDto.getFirstName(),
                customerDto.getMidName(),
                customerDto.getLastName(),
                customerDto.getEmail(),
                customerDto.getCountry(),
                customerDto.getRegion(),
                customerDto.getDistrict(),
                customerDto.getCity(),
                customerDto.getAddress(),
                customerDto.getPhone()
        );
    }
}
