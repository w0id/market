//package ru.gb.market.user.controllers;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//import ru.gb.market.user.converters.UserConverter;
//import ru.gb.market.user.converters.UserWrapperConverter;
//import ru.gb.market.user.dtos.UserDto;
//import ru.gb.market.user.services.UserService;
//import ru.gb.market.user.wrappers.UserWrapper;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/v1/users")
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserConverter userConverter;
//    private final UserWrapperConverter userWrapperConverter;
//    private final UserService userService;
//
//    @GetMapping("/{id}")
//    public UserDto getUser(@PathVariable String username) {
//        return new UserDto(userService.getUser(username));
//    }
//
//
//    @GetMapping
//    public List<UserDto> getUsers() {
//        return userService.getUsers()
//                .stream().map(
//                        userConverter::entityToDto
//                ).collect(Collectors.toList());
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//    }
//
//    @PostMapping
//    public void addUser(@RequestBody UserWrapper userWrapper) {
//        userService.save(userWrapperConverter.wrapperToEntity(userWrapper));
//    }
//
//    @PutMapping
//    public void editUser(@RequestBody UserWrapper userWrapper) {
//        userService.editUser(userWrapperConverter.wrapperToEntity(userWrapper));
//    }
//}