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
    public void saveTest() {
        Session session = new Session();
        session.setSessionId(KeyUtil.getUUID(5));
        session.setActivityId("b62367c906");
        session.setSessionAddress("教2-316");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(2018, Calendar.NOVEMBER, 2, 9, 0);
        end.set(2018, Calendar.NOVEMBER, 2, 11, 0);
        session.setStartTime(new Date(start.getTimeInMillis()));
        session.setEndTime(new Date(end.getTimeInMillis()));
        Session result = repository.save(session);
        Assert.assertNotNull(result);
    }
}