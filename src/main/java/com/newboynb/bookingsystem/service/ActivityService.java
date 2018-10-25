package com.newboynb.bookingsystem.service;

import com.newboynb.bookingsystem.dataobject.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ActivityService {

    Page<Activity> findAll(Pageable pageable);

    Page<Activity> findAllByCategoryId(String categoryId, Pageable pageable);

    Activity findById(String activityId);

    Activity save(Activity activity);
}
