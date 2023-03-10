package ru.gb.market.user.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market.user.data.Customer;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
}
