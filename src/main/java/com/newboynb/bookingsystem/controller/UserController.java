package com.newboynb.bookingsystem.controller;

import com.newboynb.bookingsystem.VO.ResultVO;
import com.newboynb.bookingsystem.VO.UserVO;
import com.newboynb.bookingsystem.constant.CookieConstant;
import com.newboynb.bookingsystem.constant.RedisConstant;
import com.newboynb.bookingsystem.dataobject.User;
import com.newboynb.bookingsystem.enums.ResultEnum;
import com.newboynb.bookingsystem.exception.BookingException;
import com.newboynb.bookingsystem.service.UserService;
import com.newboynb.bookingsystem.utils.AuthUtil;
import com.newboynb.bookingsystem.utils.CookieUtil;
import com.newboynb.bookingsystem.utils.KeyUtil;
import com.newboynb.bookingsystem.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public ResultVO login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password,
                          HttpServletResponse response) {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new BookingException(ResultEnum.USER_NOT_EXIST);
        }
        if (!password.equals(user.getPassword())) {
            throw new BookingException(ResultEnum.PASSWORD_ERROR);
        }

        //设置token
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), user.getUserId(), expire, TimeUnit.SECONDS);
        //设置token到Cookie
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return ResultVOUtil.success(null);
    }

    @GetMapping("/logout")
    public ResultVO logout(HttpServletRequest request, HttpServletResponse response) {
        //从cookie查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            //清除cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        return ResultVOUtil.success(null);
    }

    /**
     * 获取当前登录用户的userId
     * @return
     */
    @GetMapping("/getLoginUser")
    public ResultVO getLoginUserInfo(HttpServletRequest request) {
        //从cookie查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            String userId = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            User user = userService.findById(userId);
            // 如何没有该ID对应的用户返回错误信息
            if (user == null) {
                throw new BookingException(ResultEnum.USER_NOT_EXIST);
            }
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return ResultVOUtil.success(userVO);
        }
        throw new BookingException(ResultEnum.NOT_LOGIN);
    }

    /**
     * 根据用户ID查找用户信息
     * @param userId 用户ID
     * @return
     */
    @GetMapping("/user/{userId}")
    public ResultVO getUserInfo(@PathVariable(value = "userId") String userId, HttpServletRequest request) {
        //判断权限
        AuthUtil.auth(redisTemplate, request, userId);
        // 从数据库中查找用户信息
        User user = userService.findById(userId);
        // 如何没有该ID对应的用户返回错误信息
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
    public ResultVO update(@PathVariable(value = "userId") String userId, String phone, HttpServletRequest request) {
        //判断权限
        AuthUtil.auth(redisTemplate, request, userId);

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
