package ru.gb.market.user.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.market.user.converters.CustomerConverter;
import ru.gb.market.user.converters.UserWrapperConverter;
import ru.gb.market.user.dtos.CustomerDto;
import ru.gb.market.user.services.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerConverter customerConverter;
    private final UserWrapperConverter userWrapperConverter;
    private final CustomerService customerService;

    @GetMapping("/{username}")
    public CustomerDto getUser(@PathVariable String username) {
        return customerConverter.entityToDto(customerService.getUser(username));
    }

}