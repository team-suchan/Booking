package com.newboynb.bookingsystem.repository;

import com.newboynb.bookingsystem.dataobject.Session;
import com.newboynb.bookingsystem.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionRepositoryTest {

    @Autowired
    private SessionRepository repository;

    @Test
    public void findTest() {
        repository.findAll();
    }
}