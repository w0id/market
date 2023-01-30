package ru.gb.market.user.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gb.market.user.data.User;
import ru.gb.market.user.repositories.IUserRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService {

    private final IUserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

}
