package com.newboynb.bookingsystem.repository;

import com.newboynb.bookingsystem.dataobject.User;
import com.newboynb.bookingsystem.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void saveTest() {
        User user = new User();
        user.setUserId(KeyUtil.getUniqueKey());
        user.setUsername("张三");
        user.setPassword("123456");
        user.setEmail("297354270@qq.com");
        User result = repository.save(user);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByIdTest() {
        if (repository.findById("1539854673105925891").isPresent()) {
            User user = repository.findById("1539854673105925891").get();
            Assert.assertEquals("1539854673105925891", user.getUserId());
        } else {
            throw new RuntimeException();
        }
    }
}