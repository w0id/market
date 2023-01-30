package ru.gb.market.user.converters;

import org.springframework.stereotype.Component;
import ru.gb.market.user.data.Customer;
import ru.gb.market.user.dtos.CustomerDto;

@Component
public class CustomerConverter {
    public CustomerDto entityToDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getMidName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getCountry(),
                customer.getRegion(),
                customer.getDistrict(),
                customer.getCity(),
                customer.getAddress(),
                customer.getPhone()
        );
    }

}
