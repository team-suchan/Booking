package com.newboynb.bookingsystem.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(0, "成功"),
    USER_NOT_EXIST(1, "用户不存在"),
    PASSWORD_ERROR(2, "密码错误");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
