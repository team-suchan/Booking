package com.newboynb.bookingsystem.controller;

import com.newboynb.bookingsystem.VO.ResultVO;
import com.newboynb.bookingsystem.dataobject.User;
import com.newboynb.bookingsystem.enums.ResultEnum;
import com.newboynb.bookingsystem.exception.BookingException;
import com.newboynb.bookingsystem.service.UserService;
import com.newboynb.bookingsystem.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultVO login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password) {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new BookingException(ResultEnum.USER_NOT_EXIST);
        }
        if (!password.equals(user.getPassword())) {
            throw new BookingException(ResultEnum.PASSWORD_ERROR);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserId());
        return ResultVOUtil.success(map);
    }
}
