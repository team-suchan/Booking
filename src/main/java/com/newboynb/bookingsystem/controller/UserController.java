package com.newboynb.bookingsystem.controller;

import com.newboynb.bookingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public void login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {

    }
}
