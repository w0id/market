package ru.gb.market.user.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.market.user.data.User;
import ru.gb.market.user.dtos.NewUserDto;
import ru.gb.market.user.services.RegisterService;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {
    private RegisterService registerService;

    @PostMapping
    public NewUserDto registerUser(@RequestBody NewUserDto user) {
        registerService.save(new User(user.getUsername(), new BCryptPasswordEncoder().encode(user.getPassword()), user.getCustomer()));
        return user;
    }
}