package com.newboynb.bookingsystem.exception;

import com.newboynb.bookingsystem.enums.ResultEnum;
import lombok.Getter;

@Getter
public class BookingException extends RuntimeException {

    private Integer code;

    public BookingException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public BookingException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
