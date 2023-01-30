package ru.gb.market.user.converters;

import org.springframework.stereotype.Component;
import ru.gb.market.user.data.Role;
import ru.gb.market.user.dtos.RoleDto;

@Component
public class RoleConverter {
    public RoleDto entityToDto(Role role) {
        return new RoleDto(role.getId(), role.getName());
    }

    public Role dtoToEntity(RoleDto roleDto) {
        Role role = new Role();
        role.setId(role.getId());
        role.setName(roleDto.getName());
        return role;
    }
}
