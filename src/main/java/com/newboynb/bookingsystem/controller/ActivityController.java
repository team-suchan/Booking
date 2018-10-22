package com.newboynb.bookingsystem.controller;

import com.newboynb.bookingsystem.VO.ActivityVO;
import com.newboynb.bookingsystem.VO.ResultVO;
import com.newboynb.bookingsystem.dataobject.Activity;
import com.newboynb.bookingsystem.dataobject.Category;
import com.newboynb.bookingsystem.service.ActivityService;
import com.newboynb.bookingsystem.service.CategoryService;
import com.newboynb.bookingsystem.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/activity")
    public ResultVO list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "size", defaultValue = "30") Integer size) {
        PageRequest request = PageRequest.of(page - 1, size);
        Page<Activity> activityPage = activityService.findAll(request);
        List<ActivityVO> activityVOList = new ArrayList<>();
        for (Activity activity: activityPage) {
            ActivityVO activityVO = new ActivityVO();
            BeanUtils.copyProperties(activity, activityVO);
            Category category = categoryService.findById(activity.getCategoryId());
            if (category != null) {
                activityVO.setCategory(category);
            }
            activityVOList.add(activityVO);
        }
        return ResultVOUtil.success(activityVOList);
    }
}