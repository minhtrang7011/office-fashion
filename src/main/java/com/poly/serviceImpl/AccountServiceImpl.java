package com.poly.serviceImpl;

import com.poly.entity.User;
import com.poly.repository.AccountRepository;
import com.poly.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<User> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return accountRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}
