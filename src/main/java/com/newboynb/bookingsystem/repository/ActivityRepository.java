package com.newboynb.bookingsystem.repository;

import com.newboynb.bookingsystem.dataobject.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, String> {
}
