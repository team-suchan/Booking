package com.newboynb.bookingsystem.repository;

import com.newboynb.bookingsystem.dataobject.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, String> {

    List<Session> findAllByActivityId(String activityId);
}
