package com.bookverse.userservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookverse.userservice.entities.User;
import com.bookverse.userservice.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        // Test için açılışta veri ekleyelim
        userRepository.save(new User("oguzhan", "oguzhan@ornek.com"));
        userRepository.save(new User("admin", "admin@ornek.com"));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }
}