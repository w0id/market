package ru.gb.market.user.wrappers;

import lombok.Getter;

@Getter
public class AuthRequest {

    private String username;
    private String password;

}