package com.newboynb.bookingsystem.controller;

import com.newboynb.bookingsystem.VO.ResultVO;
import com.newboynb.bookingsystem.dataobject.Activity;
import com.newboynb.bookingsystem.dataobject.Session;
import com.newboynb.bookingsystem.enums.ResultEnum;
import com.newboynb.bookingsystem.exception.BookingException;
import com.newboynb.bookingsystem.form.SessionForm;
import com.newboynb.bookingsystem.service.ActivityService;
import com.newboynb.bookingsystem.service.SessionService;
import com.newboynb.bookingsystem.utils.AuthUtil;
import com.newboynb.bookingsystem.utils.KeyUtil;
import com.newboynb.bookingsystem.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("")
    public ResultVO findByActivityId(String activityId) {
        if (activityId == null) {
            throw new BookingException(ResultEnum.ACTIVITY_ID_EMPTY);
        }
        List<Session> sessionList = sessionService.findByActivityId(activityId);
        return ResultVOUtil.success(sessionList);
    }

    @PostMapping("")
    public ResultVO add(@Valid SessionForm sessionForm, HttpServletRequest request) {
        //判断权限
        sessionAuth(request, sessionForm.getActivityId());

        Session session = new Session();
        BeanUtils.copyProperties(sessionForm, session);
        session.setSessionId(KeyUtil.getUUID(10));
        Session result = sessionService.save(session);
        return ResultVOUtil.success(result);
    }

    @PutMapping("{sessionId}")
    public ResultVO update(@PathVariable(value = "sessionId") String sessionId, @Valid SessionForm sessionForm, HttpServletRequest request) {
        Session session = sessionService.findById(sessionId);
        if (session == null) {
            throw new BookingException(ResultEnum.SESSION_ID_NOT_EXIST);
        }
        //判断权限
        sessionAuth(request, sessionForm.getActivityId());

        BeanUtils.copyProperties(sessionForm, session);
        sessionService.save(session);
        return ResultVOUtil.success(session);
    }

    @DeleteMapping("{sessionId}")
    public ResultVO delete(@PathVariable(value = "sessionId") String sessionId, HttpServletRequest request) {
        Session session = sessionService.findById(sessionId);
        if (session == null) {
            throw new BookingException(ResultEnum.SESSION_ID_NOT_EXIST);
        }

        //判断权限
        sessionAuth(request, session.getActivityId());

        sessionService.delete(sessionId);
        return ResultVOUtil.success(null);
    }

    private void sessionAuth(HttpServletRequest request, String activityId) {
        //判断权限
        Activity activity = activityService.findById(activityId);
        if (activity == null) {
            throw new BookingException(ResultEnum.ACTIVITY_NOT_EXIST);
        }
        AuthUtil.auth(redisTemplate, request, activity.getOwnerId());
    }
}
