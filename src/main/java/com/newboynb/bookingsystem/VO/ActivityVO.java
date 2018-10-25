package com.newboynb.bookingsystem.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.newboynb.bookingsystem.dataobject.Category;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ActivityVO {

    private String activityId;

    private String activityName;

    private Float ActivityGrade;

//    @JsonSerialize(using = Date2LongSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date activityDate;

    private String activityDescribe;

    private String activityIcon;

    private Category category;

    private String ownerId;

    private String activityAddress;

    private String bookingStatus;
}
