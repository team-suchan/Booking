package com.newboynb.bookingsystem.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Category {

    @Id
    private String categoryId;

    private String categoryName;
}
