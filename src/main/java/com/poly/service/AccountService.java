package com.poly.service;

import com.poly.entity.User;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void deleteById(Long id);
}
