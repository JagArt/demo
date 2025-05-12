package com.example.demo.service;

import com.example.demo.domail.dto.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User create(User user);

    void delete(Long id);

    User update(Long id, String email, String name);
}
