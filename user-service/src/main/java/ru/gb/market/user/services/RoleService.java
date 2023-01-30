package ru.gb.market.user.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market.user.data.Role;
import ru.gb.market.user.repositories.IRoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final IRoleRepository roleRepository;

    public Role getRole(String name) {
        return roleRepository.findByName(name);
    }
}
