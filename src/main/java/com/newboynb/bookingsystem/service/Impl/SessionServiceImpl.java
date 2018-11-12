package com.newboynb.bookingsystem.service.Impl;

import com.newboynb.bookingsystem.dataobject.Session;
import com.newboynb.bookingsystem.repository.SessionRepository;
import com.newboynb.bookingsystem.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository repository;

    @Override
    public List<Session> findByActivityId(String activityId) {
        return repository.findAllByActivityId(activityId);
    }

    @Override
    public Session save(Session session) {
        return repository.save(session);
    }

    @Override
    public Session findById(String sessionId) {
        return repository.findById(sessionId).orElse(null);
    }

    @Override
    public void delete(String sessionId) {
        repository.deleteById(sessionId);
    }
}
