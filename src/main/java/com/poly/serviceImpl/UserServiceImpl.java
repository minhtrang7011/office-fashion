package com.poly.serviceImpl;

import com.poly.model.User;
import com.poly.repository.UserRepository;
import com.poly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.empty();
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.empty();
    }
}
