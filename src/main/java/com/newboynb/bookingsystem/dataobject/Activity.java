package com.newboynb.bookingsystem.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Activity {

    @Id
    private String activityId;

    private String activityName;

    private Float ActivityGrade;

    private Date activityDate;

    private Integer duration;

    private String activityDescribe;

    private String activityIcon;

    private String categoryId;

    private String ownerId;
}
