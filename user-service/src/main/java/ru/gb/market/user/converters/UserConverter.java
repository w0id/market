package ru.gb.market.user.converters;

import org.springframework.stereotype.Component;
import ru.gb.market.user.data.User;
import ru.gb.market.user.dtos.UserDto;

@Component
public class UserConverter {
    public UserDto entityToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getCustomer(),
                user.getRoles()
                );
    }
}

