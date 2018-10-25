package com.newboynb.bookingsystem.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ActivityForm {

    private String activityName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date activityDate;

    private String activityDescribe;

    private String activityIcon;

    private String categoryId;

    private String ownerId;

    private String activityAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startBookTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endBookTime;
}
