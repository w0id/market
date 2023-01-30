package ru.gb.market.user.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.market.user.data.Role;
import ru.gb.market.user.data.User;
import ru.gb.market.user.repositories.IUserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;
    private final RoleService roleService;


    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Пользователь %s не найден", username)));
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Page<User> getUserFilter(Integer page) {
        return userRepository.findAll(PageRequest.of(page - 1, 10, Sort.by("id").ascending()));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void save(User user) {
        Collection<Role> roles = user.getRoles();
        roles.stream().forEach(r -> r.setId(roleService.getRole(r.getName()).getId()));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void editUser(User user) {
        user.setId(userRepository.findByUsername(user.getUsername()).get().getId());
        Collection<Role> roles = user.getRoles();
        roles.stream().forEach(r -> r.setId(roleService.getRole(r.getName()).getId()));
        user.setRoles(roles);
        userRepository.save(user);
    }
}
