package com.newboynb.bookingsystem.VO;

import com.newboynb.bookingsystem.dataobject.Category;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityVO {

    private String activityId;

    private String activityName;

    private Float ActivityGrade;

    private Date activityDate;

    private String activityDescribe;

    private String activityIcon;

    private Category category;

    private String ownerId;
}
