package com.newboynb.bookingsystem.service;

import com.newboynb.bookingsystem.dataobject.User;

public interface UserService {

    User findByEmail(String email);

    User findById(String userId);
}
