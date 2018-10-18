package com.newboynb.bookingsystem.repository;

import com.newboynb.bookingsystem.dataobject.Activity;
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
public class ActivityRepositoryTest {

    @Autowired
    private ActivityRepository repository;

    @Test
    public void saveTest() {
        Activity activity = new Activity();
        activity.setActivityId(KeyUtil.getUUID(10));
        activity.setActivityName("张三");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.OCTOBER, 30);
        activity.setActivityDate(new Date(calendar.getTimeInMillis()));
        activity.setDuration(60);
        activity.setCategoryId(KeyUtil.getUUID(10));
        activity.setOwnerId(KeyUtil.getUUID(10));
        System.out.println(activity);
        Activity result = repository.save(activity);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByIdTest() {
        if (repository.findById("1539766400243509682").isPresent()) {
            repository.findById("1539766400243509682").get();
        } else {
            throw new RuntimeException();
        }
    }

    @Test
    public void findAllTest() {
    }
}