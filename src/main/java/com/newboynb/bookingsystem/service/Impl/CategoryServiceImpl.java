package com.newboynb.bookingsystem.service.Impl;

import com.newboynb.bookingsystem.dataobject.Category;
import com.newboynb.bookingsystem.repository.CategoryRepository;
import com.newboynb.bookingsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category findById(String categoryId) {
        return repository.findById(categoryId).orElse(null);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }
}
