package com.newboynb.bookingsystem.service;

import com.newboynb.bookingsystem.dataobject.User;

import java.util.List;

public interface UserService {

    User findByEmail(String email);

    User findById(String userId);

    List<User> findAll();

    User save(User user);
}
