package com.newboynb.bookingsystem.utils;

import com.newboynb.bookingsystem.constant.CookieConstant;
import com.newboynb.bookingsystem.constant.RedisConstant;
import com.newboynb.bookingsystem.enums.ResultEnum;
import com.newboynb.bookingsystem.exception.BookingException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class AuthUtil {

    /**
     * 判断登录权限
     * @param request
     * @param userId
     */
    public static void auth(StringRedisTemplate redisTemplate, HttpServletRequest request, String userId) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            throw new BookingException(ResultEnum.AUTH_FAIL);
        }
        System.out.println("【cookie】" + cookie.getValue());
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue) || !tokenValue.equals(userId)) {
            throw new BookingException(ResultEnum.AUTH_FAIL);
        }
    }
}
