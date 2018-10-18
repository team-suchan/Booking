package com.newboynb.bookingsystem.service.Impl;

import com.newboynb.bookingsystem.dataobject.Activity;
import com.newboynb.bookingsystem.repository.ActivityRepository;
import com.newboynb.bookingsystem.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository repository;

    @Override
    public Page<Activity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
