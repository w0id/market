package ru.gb.market.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ru.gb.market.user.data.Role;

@Component
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
