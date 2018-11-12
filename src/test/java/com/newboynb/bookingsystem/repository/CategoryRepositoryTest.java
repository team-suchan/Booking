package com.newboynb.bookingsystem.repository;

import com.newboynb.bookingsystem.dataobject.Category;
import com.newboynb.bookingsystem.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void saveTest() {
        Category category = new Category();
        category.setCategoryId(KeyUtil.getUniqueKey());
        category.setCategoryName("电影");
        Category result = repository.save(category);
        Assert.assertNotNull(result);
    }
}