package com.newboynb.bookingsystem.service;

import com.newboynb.bookingsystem.dataobject.Session;

import java.util.List;

public interface SessionService {

    Session findById(String sessionId);

    List<Session> findByActivityId(String Activity);

    Session save(Session session);

    void delete(String sessionId);
}
