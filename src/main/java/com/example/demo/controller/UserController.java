package com.example.demo.controller;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.mapper.Mapper;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private Mapper<UserEntity, UserDto> userMapper;

    @GetMapping
    public List<UserDto> findAll() {
        List<UserEntity> userEntities =userService.findAll();
        List<UserDto> userDtos = userEntities.stream()
                .map(userMapper::mapTo)
                .toList();
        return userDtos;
    }

    @PostMapping
    public UserEntity create(@RequestBody UserEntity user) {
//        UserDto userDto = userMapper.mapTo(user);

        return userService.create(user);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping(path = "{id}")
    public UserEntity update(
            @PathVariable Long id,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String name
    ) {
        return userService.update(id, email, name);
    }
}
