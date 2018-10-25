package com.newboynb.bookingsystem.enums;

import lombok.Getter;

@Getter
public enum BookingStatusEnum {

    BEFORE(0, "未到预定时间"),
    CURRENT(1, "可预定"),
    AFTER(2, "已过预定时间");

    private Integer code;

    private String status;

    BookingStatusEnum(Integer code, String status) {
        this.code = code;
        this.status = status;
    }
}
