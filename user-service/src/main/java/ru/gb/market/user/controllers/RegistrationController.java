package ru.gb.market.user.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.market.user.services.RegisterService;

@RestController
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {
    private RegisterService registerService;

}