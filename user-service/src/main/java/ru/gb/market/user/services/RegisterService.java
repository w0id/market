package ru.gb.market.user.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.market.user.data.User;
import ru.gb.market.user.dtos.NewUserDto;
import ru.gb.market.user.dtos.UserDto;
import ru.gb.market.user.repositories.IUserRepository;

import java.util.Collections;


@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService {

    private final IUserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

}
