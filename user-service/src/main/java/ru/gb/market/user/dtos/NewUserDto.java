package ru.gb.market.user.dtos;

import lombok.*;
import ru.gb.market.user.data.Customer;
import ru.gb.market.user.data.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserDto {

    private String username;
    private String password;
    private Customer customer;

    public NewUserDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.customer = user.getCustomer();
    }

}
