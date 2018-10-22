package com.newboynb.bookingsystem.handler;

import com.newboynb.bookingsystem.VO.ResultVO;
import com.newboynb.bookingsystem.exception.BookingException;
import com.newboynb.bookingsystem.utils.ResultVOUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = BookingException.class)
    @ResponseBody
    public ResultVO handlerBookingException(BookingException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
