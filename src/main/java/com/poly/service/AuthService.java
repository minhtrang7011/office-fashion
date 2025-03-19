package com.poly.service;

import com.poly.model.User;
import com.poly.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public User authenticate(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.filter(u -> u.getPassword().equals(password)).orElse(null);
    }

    public void register(String username, String password, String email, String phone, String address) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setAddress(address);

        // ✅ Set default role (e.g., 2 for regular user)
        user.setRoleId(2);  // Assuming 2 is the default role for normal users

        userRepository.save(user);
    }


    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
