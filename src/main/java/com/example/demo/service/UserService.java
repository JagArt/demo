package com.example.demo.service;

import com.example.demo.repository.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User create(User user);

    void delete(Long id);

    User update(Long id, String email, String name);
}
