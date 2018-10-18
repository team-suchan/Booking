package com.newboynb.bookingsystem.repository;

import com.newboynb.bookingsystem.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);
}
