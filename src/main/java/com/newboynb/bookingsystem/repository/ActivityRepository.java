package com.newboynb.bookingsystem.repository;

import com.newboynb.bookingsystem.dataobject.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, String> {

    Page<Activity> findAllByCategoryId(String categoryId, Pageable pageable);
}
