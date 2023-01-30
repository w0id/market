package ru.gb.market.user.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.market.user.data.Customer;
import ru.gb.market.user.data.User;
import ru.gb.market.user.repositories.IUserRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final IUserRepository userRepository;
    private final RoleService roleService;



    public Customer getUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь %s не найден", username)));
        return user.getCustomer();
    }

}
