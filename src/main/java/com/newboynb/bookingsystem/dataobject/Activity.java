package com.newboynb.bookingsystem.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class Activity {

    @Id
    private String activityId;

    private String activityName;

    private Float ActivityGrade;

    private Date activityDate;

    private String activityDescribe;

    private String activityIcon;

    private String categoryId;

    private String ownerId;

    private String activityAddress;

    private Date startBookTime;

    private Date endBookTime;
}
