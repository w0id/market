package ru.gb.market.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.market.user.data.Customer;
import ru.gb.market.user.data.Role;
import ru.gb.market.user.data.User;

import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private Customer customer;
    private Collection<Role> roles;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.roles = user.getRoles();
        this.customer = user.getCustomer();
    }

}
