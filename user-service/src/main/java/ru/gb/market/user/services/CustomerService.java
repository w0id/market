package ru.gb.market.user.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.market.user.data.Customer;
import ru.gb.market.user.repositories.ICustomerRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final ICustomerRepository customerRepository;



    public Customer getUser(String username) {
        Customer customer = customerRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь %s не найден", username)));
        return customer;
    }

}
