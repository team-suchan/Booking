package com.newboynb.bookingsystem.service;

import com.newboynb.bookingsystem.dataobject.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityService {

    Page<Activity> findAll(Pageable pageable);
}
