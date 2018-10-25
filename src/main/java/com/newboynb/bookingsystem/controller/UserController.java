package com.newboynb.bookingsystem.controller;

import com.newboynb.bookingsystem.VO.ResultVO;
import com.newboynb.bookingsystem.VO.UserVO;
import com.newboynb.bookingsystem.dataobject.User;
import com.newboynb.bookingsystem.enums.ResultEnum;
import com.newboynb.bookingsystem.exception.BookingException;
import com.newboynb.bookingsystem.service.UserService;
import com.newboynb.bookingsystem.utils.KeyUtil;
import com.newboynb.bookingsystem.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
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
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultVOUtil.success(userVO);
    }

    @GetMapping("/user/{userId}")
    public ResultVO getUserInfo(@PathVariable(value = "userId") String userId) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new BookingException(ResultEnum.USER_NOT_EXIST);
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultVOUtil.success(userVO);
    }

    @PostMapping("/user")
    public ResultVO register(@Valid User form) {
        User user = userService.findByEmail(form.getEmail());
        if (user != null) {
            throw new BookingException(ResultEnum.EMAIL_EXIST);
        }

        List<User> userList = userService.findAll();
        for (User u: userList) {
            if (form.getUsername().equals(u.getUsername())) {
                throw new BookingException(ResultEnum.USERNAME_EXIST);
            }
        }

        String userId = KeyUtil.getUUID(10);
        form.setUserId(userId);
        User result = userService.save(form);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(result, userVO);
        return ResultVOUtil.success(userVO);
    }

    @PutMapping("/user/{userId}")
    public ResultVO update(@PathVariable(value = "userId") String userId, String phone) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new BookingException(ResultEnum.USER_NOT_EXIST);
        }
        user.setPhone(phone);
        User result = userService.save(user);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(result, userVO);
        return ResultVOUtil.success(userVO);
    }

    @PostMapping("/user/findPassword")
    public ResultVO findPassword(String email, String phone, String password) {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new BookingException(ResultEnum.USER_NOT_EXIST);
        }
        if (!phone.equals(user.getPhone())) {
            throw new BookingException(ResultEnum.PHONE_MISMATCHING);
        }
        user.setPassword(password);
        User result = userService.save(user);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(result, userVO);
        return ResultVOUtil.success(userVO);
    }
}
