package com.newboynb.bookingsystem.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),
    USER_NOT_EXIST(1, "该用户不存在"),
    PASSWORD_ERROR(2, "密码错误"),
    EMAIL_EXIST(3, "该邮箱已被注册"),
    USERNAME_EXIST(4, "该用户名已被注册"),
    ACTIVITY_NOT_EXIST(5, "该活动不存在"),
    PHONE_MISMATCHING(6, "手机号与帐号不匹配"),
    ACTIVITY_ID_EMPTY(7, "活动ID为空"),
    SESSION_ID_NOT_EXIST(8, "不存在该场次ID"),
    AUTH_FAIL(9, "你没有此权限进行该操作或未登录！");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
