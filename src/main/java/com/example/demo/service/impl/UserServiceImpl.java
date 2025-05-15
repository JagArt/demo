package com.example.demo.service.impl;

import com.example.demo.domain.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity create(UserEntity user) {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new IllegalStateException("User already exist");
        }
        user.setAge(Period.between(user.getBirth(), LocalDate.now()).getYears());
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User with id:" + id + " not exist");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UserEntity update(Long id, String email, String name) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("User with id:" + id + " not exist");
        }
        UserEntity user = optionalUser.get();
        if (email != null && !email.equals(user.getEmail())) {
            Optional<UserEntity> foundByEmail = userRepository.findByEmail(user.getEmail());
            if (foundByEmail.isPresent()) {
                throw new IllegalStateException("User already exist");
            }
            user.setEmail(email);
        }

        if (name != null && !name.equals(user.getName())) {
            user.setName(name);
        }
        return user;
    }
}
