package ru.gb.market.user.converters;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.gb.market.user.data.User;
import ru.gb.market.user.wrappers.UserWrapper;

@Component
public class UserWrapperConverter {

    public User wrapperToEntity(UserWrapper userWrapper) {
        if (userWrapper.getRoles() == null) {
            return new User(userWrapper.getUsername(), new BCryptPasswordEncoder().encode(userWrapper.getPassword()), userWrapper.getCustomer());
        } else {
            return new User(userWrapper.getUsername(), new BCryptPasswordEncoder().encode(userWrapper.getPassword()), userWrapper.getCustomer(), userWrapper.getRoles());
        }
    }
}
