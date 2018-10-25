package com.newboynb.bookingsystem.service;

import com.newboynb.bookingsystem.dataobject.Category;

import java.util.List;

public interface CategoryService {

    Category findById(String categoryId);

    List<Category> findAll();
}
