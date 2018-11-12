package com.newboynb.bookingsystem.dataobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Session {

    @Id
    private String sessionId;

    private String activityId;

    private String sessionAddress;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;

    private String remark;
}
