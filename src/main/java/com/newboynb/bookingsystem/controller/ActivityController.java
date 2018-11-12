package com.newboynb.bookingsystem.controller;

import com.newboynb.bookingsystem.VO.ActivityVO;
import com.newboynb.bookingsystem.VO.ResultVO;
import com.newboynb.bookingsystem.dataobject.Activity;
import com.newboynb.bookingsystem.dataobject.Category;
import com.newboynb.bookingsystem.enums.BookingStatusEnum;
import com.newboynb.bookingsystem.enums.ResultEnum;
import com.newboynb.bookingsystem.exception.BookingException;
import com.newboynb.bookingsystem.form.ActivityForm;
import com.newboynb.bookingsystem.service.ActivityService;
import com.newboynb.bookingsystem.service.CategoryService;
import com.newboynb.bookingsystem.utils.AuthUtil;
import com.newboynb.bookingsystem.utils.KeyUtil;
import com.newboynb.bookingsystem.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResultVO list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "size", defaultValue = "16") Integer size, String categoryId) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<Activity> activityPage;
        if (categoryId == null) {
            activityPage = activityService.findAll(request);
        } else {
            activityPage = activityService.findAllByCategoryId(categoryId, request);
        }
        List<ActivityVO> activityVOList = new ArrayList<>();
        for (Activity activity: activityPage) {
            activityVOList.add(getActivityVO(activity));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("activityList", activityVOList);
        map.put("totalPages", activityPage.getTotalPages());
        map.put("currentPage", page);
        return ResultVOUtil.success(map);
    }

    @GetMapping("{activityId}")
    public ResultVO findOneActivity(@PathVariable(value = "activityId") String activityId) {
        Activity activity = activityService.findById(activityId);
        if (activity == null) {
            throw new BookingException(ResultEnum.ACTIVITY_NOT_EXIST);
        }
        ActivityVO activityVO = new ActivityVO();
        BeanUtils.copyProperties(activity, activityVO);
        Category category = categoryService.findById(activity.getCategoryId());
        if (category != null) {
            activityVO.setCategory(category);
        }
        activityVO.setBookingStatus(getBookingStatus(activity.getStartBookTime().getTime(), activity.getEndBookTime().getTime()));
        return ResultVOUtil.success(activityVO);
    }

    @GetMapping("/findByUserId")
    public ResultVO findByUserId(String userId) {
        List<Activity> activityList = activityService.findByUserId(userId);
        List<ActivityVO> activityVOList = new ArrayList<>();
        for (Activity activity : activityList) {
            activityVOList.add(getActivityVO(activity));
        }
        return ResultVOUtil.success(activityVOList);
    }

    @PostMapping("")
    public ResultVO add(@Valid ActivityForm form) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(form, activity);
        activity.setActivityId(KeyUtil.getUUID(10));
        activityService.save(activity);
        Map<String, Object> map = new HashMap<>();
        map.put("activityId", activity.getActivityId());
        return ResultVOUtil.success(map);
    }

    @PutMapping("/{activityId}")
    public ResultVO update(@Valid ActivityForm form, @PathVariable(value = "activityId") String activityId, HttpServletRequest request) {
        Activity activity = activityService.findById(activityId);
        if (activity == null) {
            throw new BookingException(ResultEnum.ACTIVITY_NOT_EXIST);
        }
        //判断权限
        AuthUtil.auth(request, activity.getOwnerId());

        BeanUtils.copyProperties(form, activity);
        activityService.save(activity);
        return ResultVOUtil.success(null);
    }

    private ActivityVO getActivityVO(Activity activity) {
        ActivityVO activityVO = new ActivityVO();
        BeanUtils.copyProperties(activity, activityVO);
        Category category = categoryService.findById(activity.getCategoryId());
        if (category != null) {
            activityVO.setCategory(category);
        }
        activityVO.setBookingStatus(getBookingStatus(activity.getStartBookTime().getTime(), activity.getEndBookTime().getTime()));
        return activityVO;
    }

    private String getBookingStatus(long startTime, long endTime) {
        long now = System.currentTimeMillis();
        if (now >= startTime && now <= endTime) {
            return BookingStatusEnum.CURRENT.getStatus();
        } else if (now < startTime) {
            return BookingStatusEnum.BEFORE.getStatus();
        } else {
            return BookingStatusEnum.AFTER.getStatus();
        }
    }
}