package ru.gb.market.user.wrappers;

import lombok.Data;
import ru.gb.market.user.data.Customer;
import ru.gb.market.user.data.Role;

import java.util.List;

@Data
public class UserWrapper {
    private Long id;
    private String username;
    private String password;
    private Customer customer;
    private List<Role> roles;
}
