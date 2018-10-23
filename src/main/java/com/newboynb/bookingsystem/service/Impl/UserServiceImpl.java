package com.newboynb.bookingsystem.service.Impl;

import com.newboynb.bookingsystem.dataobject.User;
import com.newboynb.bookingsystem.repository.UserRepository;
import com.newboynb.bookingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User findById(String userId) {
        return repository.findById(userId).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }
}
