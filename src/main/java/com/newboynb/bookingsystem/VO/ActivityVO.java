package com.newboynb.bookingsystem.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.newboynb.bookingsystem.dataobject.Category;
import com.newboynb.bookingsystem.utils.Date2LongSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityVO {

    private String activityId;

    private String activityName;

    private Float ActivityGrade;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date activityDate;

    private String activityDescribe;

    private String activityIcon;

    private Category category;

    private String ownerId;

    private String activityAddress;
}
