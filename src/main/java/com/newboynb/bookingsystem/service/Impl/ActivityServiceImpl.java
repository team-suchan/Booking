package com.newboynb.bookingsystem.service.Impl;

import com.newboynb.bookingsystem.dataobject.Activity;
import com.newboynb.bookingsystem.repository.ActivityRepository;
import com.newboynb.bookingsystem.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository repository;

    @Override
    public Page<Activity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Activity> findAllByCategoryId(String categoryId, Pageable pageable) {
        return repository.findAllByCategoryId(categoryId, pageable);
    }

    @Override
    public Activity findById(String activityId) {
        return repository.findById(activityId).orElse(null);
    }

    @Override
    public Activity save(Activity activity) {
        return repository.save(activity);
    }
}
