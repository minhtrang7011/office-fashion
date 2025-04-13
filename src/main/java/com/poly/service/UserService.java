package com.poly.service;

import com.poly.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    void saveUser(User user);
    void deleteUser(Long id);
    Optional<User> getUserByUsername(String username);
    User login(String username, String password);
    User findByUsername(String username);
}
