package com.newboynb.bookingsystem.repository;

import com.newboynb.bookingsystem.dataobject.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
