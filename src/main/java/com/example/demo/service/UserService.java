package com.example.demo.service;

import com.example.demo.domain.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> findAll();

    UserEntity create(UserEntity user);

    void delete(Long id);

    UserEntity update(Long id, String email, String name);
}
